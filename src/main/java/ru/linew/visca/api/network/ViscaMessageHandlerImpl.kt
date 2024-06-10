package ru.linew.visca.api.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.example.visca.api.network.ViscaMessageHandler
import ru.linew.visca.api.network.model.ConnectionState
import ru.linew.visca.api.network.model.ViscaResponse

class ViscaMessageHandlerImpl(
    client: ViscaClient,
    scope: CoroutineScope
) : ViscaMessageHandler {

    override val messageFlow: MutableSharedFlow<ViscaResponse> =
        MutableSharedFlow(extraBufferCapacity = 10)

    init {
        var job: Job? = null
        scope.launch {
            client.connectionState.collect {
                when (it) {
                    ConnectionState.DISCONNECTED -> {
                        job?.cancel()
                        job = null
                    }

                    ConnectionState.CONNECTING -> {}

                    ConnectionState.CONNECTED -> {
                        job = launch {
                            client.messageFlow.collect { message ->
                                messageFlow.emit(message)
                            }
                        }
                    }
                }
            }
        }
    }
}