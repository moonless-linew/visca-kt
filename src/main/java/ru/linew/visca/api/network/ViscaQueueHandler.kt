package ru.linew.visca.api.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import ru.linew.visca.api.ViscaQuery
import ru.linew.visca.api.network.model.QueryRequest
import ru.linew.visca.api.network.model.ViscaResponse
import java.util.UUID

@OptIn(FlowPreview::class, ExperimentalStdlibApi::class)
class ViscaQueueHandler(
    private val client: ViscaClient,
    private val messageHandler: ViscaMessageHandlerImpl,
    coroutineScope: CoroutineScope
) {

    private val eventsFlow =
        MutableSharedFlow<Pair<String, ViscaResponse>>()

    private val queryFlow =
        MutableSharedFlow<QueryRequest>(extraBufferCapacity = 10)

    init {
        coroutineScope.launch {
            queryFlow
                .debounce(75L)
                .buffer(100)
                .collect {
                    for (i in 0 until 3) {
                        try {
                            withTimeout(2500) {
                                client.sendQuery(it.query)
                                println("--> ${it.query.javaClass.name} ${it.query.buildBytes().toHexString()}")
                                val response = messageHandler.messageFlow.first {
                                    it.status !is ViscaResponse.ViscaResponseStatus.SuccessResponse.Empty.ACK
                                }
                                println("<-- $response")
                                eventsFlow.emit(Pair(it.transactionId, response))
                            }
                            return@collect
                        } catch (e: TimeoutCancellationException) {
                            if (!it.repeat) break
                            continue
                        }
                    }
                    eventsFlow.emit(
                        Pair(
                            it.transactionId,
                            ViscaResponse(ViscaResponse.ViscaResponseStatus.Error.TimeoutError, "")
                        )
                    )
                }
        }
    }


    suspend fun sendQueryWithResult(
        query: ViscaQuery,
        repeatOnTimeout: Boolean = true
    ): ViscaResponse {
        val transactionId = UUID.randomUUID().toString()
        queryFlow.emit(
            QueryRequest(
                transactionId = transactionId,
                query = query,
                repeat = repeatOnTimeout
            )
        )
        val result = (eventsFlow.first { it.first == transactionId }).second
        return result
    }
}

