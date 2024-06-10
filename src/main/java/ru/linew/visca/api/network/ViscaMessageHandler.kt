package org.example.visca.api.network

import kotlinx.coroutines.flow.Flow
import ru.linew.visca.api.network.model.ViscaResponse

interface ViscaMessageHandler {

    val messageFlow: Flow<ViscaResponse>

}