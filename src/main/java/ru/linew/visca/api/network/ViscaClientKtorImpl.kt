package ru.linew.visca.api.network

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.ConnectedDatagramSocket
import io.ktor.network.sockets.Datagram
import io.ktor.network.sockets.InetSocketAddress
import io.ktor.network.sockets.aSocket
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext
import ru.linew.visca.api.ViscaQuery
import ru.linew.visca.api.network.mapper.toViscaResponse
import ru.linew.visca.api.network.model.ConnectArtifacts
import ru.linew.visca.api.network.model.ConnectionState
import ru.linew.visca.api.network.model.ViscaResponse
import ru.linew.visca.api.queries.inquery.pantilt.PanTiltPositionCommand

internal class ViscaClientKtorImpl : ViscaClient {

    override val connectionState: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.DISCONNECTED)

    override val messageFlow
        get() = socket!!.incoming
            .receiveAsFlow()
            .map { it.packet.readBytes().toViscaResponse() }
    private val selectorManager = SelectorManager(Dispatchers.IO)
    private var socket: ConnectedDatagramSocket? = null

    override suspend fun connect(connectArtifacts: ConnectArtifacts) {
        connectionState.value = ConnectionState.CONNECTING

        socket = aSocket(selectorManager)
            .udp()
            .connect(
                localAddress = InetSocketAddress("0.0.0.0", 34254),
                remoteAddress = InetSocketAddress(connectArtifacts.ip, connectArtifacts.port)
            )

        sendQuery(PanTiltPositionCommand)

        val ans = messageFlow
            .filter { it.status != ViscaResponse.ViscaResponseStatus.SuccessResponse.Empty.ACK }
            .first()

        if (ans.status is ViscaResponse.ViscaResponseStatus.SuccessResponse) {
            connectionState.value = ConnectionState.CONNECTED
        } else {
            withContext(Dispatchers.IO) {
                socket!!.close()
                socket = null
            }
            connectionState.value = ConnectionState.DISCONNECTED
        }
    }

    override suspend fun sendQuery(query: ViscaQuery) {
        require(socket != null && connectionState.value != ConnectionState.DISCONNECTED)
        socket!!.send(Datagram(ByteReadPacket(array = query.buildBytes()), socket!!.remoteAddress))
    }

    override suspend fun disconnect() {
        socket?.dispose()
        socket = null
    }
}


