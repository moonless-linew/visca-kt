package ru.linew.visca.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.linew.visca.api.configurator.ViscaCameraConfigurator
import ru.linew.visca.api.configurator.model.AvailableValuesConfig
import ru.linew.visca.api.configurator.model.ViscaCameraState
import ru.linew.visca.api.network.ViscaClientKtorImpl
import ru.linew.visca.api.network.ViscaMessageHandlerImpl
import ru.linew.visca.api.network.ViscaQueueHandler
import ru.linew.visca.api.network.model.ConnectArtifacts
import ru.linew.visca.api.network.model.ConnectionState
import ru.linew.visca.api.network.model.ViscaResponse
import ru.linew.visca.api.queries.command.color.whitebalance.ColorWhiteBalanceMode
import ru.linew.visca.api.queries.command.exposure.gain.GainDirect
import ru.linew.visca.api.queries.command.exposure.mode.ExposureModeChange
import ru.linew.visca.api.queries.inquery.exposure.ExposureGain
import ru.linew.visca.api.queries.inquery.exposure.ExposureIris
import ru.linew.visca.api.queries.inquery.exposure.ExposureShutter
import ru.linew.visca.api.queries.inquery.whitebalance.WhiteBalanceBGain
import ru.linew.visca.api.queries.inquery.whitebalance.WhiteBalanceMode
import ru.linew.visca.api.queries.inquery.whitebalance.WhiteBalanceRGain

//todo
class ViscaCamera(val ip: String, val port: Int) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val viscaClient = ViscaClientKtorImpl()
    private val messageHandler = ViscaMessageHandlerImpl(viscaClient, coroutineScope)
    private val queueHandler = ViscaQueueHandler(
        client = viscaClient,
        messageHandler = messageHandler,
        coroutineScope = coroutineScope
    )
    private val configurator = ViscaCameraConfigurator(queueHandler)

    val connectionState: StateFlow<ConnectionState> = viscaClient.connectionState

    private val _cameraState = MutableStateFlow(ViscaCameraState())
    val cameraState: StateFlow<ViscaCameraState>
        get() = _cameraState.asStateFlow()

    val fullIp: String
        get() = "$ip:$port"

    private val isConnected: Boolean
        get() = connectionState.value == ConnectionState.CONNECTED

    suspend fun connect() {
        viscaClient.connect(ConnectArtifacts(ip, port))
    }

    suspend fun sendQuery(query: ViscaQuery, repeatOnTimeout: Boolean = true): ViscaResponse {
        assert(isConnected)
        return queueHandler.sendQueryWithResult(query, repeatOnTimeout)
    }

    suspend fun getAvailableValues(): AvailableValuesConfig {
        sendQuery(ExposureModeChange(ExposureModeChange.ExposureMode.MANUAL))
        sendQuery(ColorWhiteBalanceMode(ColorWhiteBalanceMode.WhiteBalanceMode.MANUAL))
        val result = configurator.configure()
        sendQuery(ColorWhiteBalanceMode(ColorWhiteBalanceMode.WhiteBalanceMode.MANUAL))
        val res2 = sendQuery(ExposureGain)
        val res3 = sendQuery(
            GainDirect((res2.status as? ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion)?.args ?: "")
        )
        if (res3.status is ViscaResponse.ViscaResponseStatus.Error) {
            return result.copy(brokenGain = true)
        }
        return result
    }

    //to other class
    suspend fun updateState() {
        val rGainValue =
            (queueHandler.sendQueryWithResult(WhiteBalanceRGain).status
                    as? ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion)?.args
        val bGainValue =
            (queueHandler.sendQueryWithResult(WhiteBalanceBGain).status
                    as? ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion)?.args
        val gainValue =
            (queueHandler.sendQueryWithResult(ExposureGain).status
                    as? ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion)?.args
        val shutterValue =
            (queueHandler.sendQueryWithResult(ExposureShutter).status
                    as? ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion)?.args
        val irisValue =
            (queueHandler.sendQueryWithResult(ExposureIris).status
                    as? ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion)?.args
        val whiteBalanceMode =
            (queueHandler.sendQueryWithResult(WhiteBalanceMode).status
                    as? ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion)?.args
        _cameraState.update {
            it.copy(
                rGainValue = rGainValue,
                bGainValue = bGainValue,
                gainValue = gainValue,
                shutterValue = shutterValue,
                irisValue = irisValue,
                whiteBalanceMode = whiteBalanceMode
            )
        }
    }


    suspend fun disconnect() {
        viscaClient.disconnect()
        coroutineScope.cancel()
    }
}