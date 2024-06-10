package ru.linew.visca.api.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.linew.visca.api.ViscaQuery
import ru.linew.visca.api.network.model.ConnectArtifacts
import ru.linew.visca.api.network.model.ConnectionState
import ru.linew.visca.api.network.model.ViscaResponse

interface ViscaClient {

    val connectionState: StateFlow<ConnectionState>

    val messageFlow: Flow<ViscaResponse>

    suspend fun connect(connectArtifacts: ConnectArtifacts)

    suspend fun sendQuery(query: ViscaQuery)

    suspend fun disconnect()

}