package ru.linew.visca.api.configurator.configurators

import ru.linew.visca.api.network.ViscaQueueHandler
import ru.linew.visca.api.network.model.ViscaResponse
import ru.linew.visca.api.queries.command.ViscaCommand
import ru.linew.visca.api.queries.inquery.ViscaInquery

class GenericConfigurator(
    private val queueHandler: ViscaQueueHandler,
    private val upCommand: ViscaCommand,
    private val downCommand: ViscaCommand,
    private val inqueryCommand: ViscaInquery,
    private val setOriginalValueCallback: suspend (value: String) -> Unit
) {
    suspend fun getAvailableValues(): List<String> {

        val originalValueResult = queueHandler.sendQueryWithResult(inqueryCommand)
        val originalValue =
            if (originalValueResult.status is ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion) {
                originalValueResult.status.args
            } else {
                null
            }

        var buffer: String? = null
        for (i in 0 until 100) {
            val value = queueHandler.sendQueryWithResult(inqueryCommand)
            queueHandler.sendQueryWithResult(upCommand)
            if (buffer == value.rawMessage) break
            buffer = value.rawMessage
        }

        buffer = null
        val values = mutableListOf<String>()
        for (i in 0 until 100) {
            val value = queueHandler.sendQueryWithResult(inqueryCommand)
            queueHandler.sendQueryWithResult(downCommand)
            if (buffer != value.rawMessage) {
                buffer = value.rawMessage
                values.add((value.status as ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion).args)
            } else {
                break
            }
        }

        originalValue?.let {
            setOriginalValueCallback(it)
        }

        return values
    }
}