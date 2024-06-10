package ru.linew.visca.api.network.mapper

import ru.linew.visca.api.network.model.ViscaResponse
import ru.linew.visca.api.responses.error.CommandBufferFull
import ru.linew.visca.api.responses.error.CommandCanceled
import ru.linew.visca.api.responses.error.CommandNotExecutable
import ru.linew.visca.api.responses.error.MessageLengthError
import ru.linew.visca.api.responses.error.NoSocket
import ru.linew.visca.api.responses.error.SyntaxError
import ru.linew.visca.api.responses.success.empty.ACK
import ru.linew.visca.api.responses.success.empty.Completion

@OptIn(ExperimentalStdlibApi::class)
fun ByteArray.toViscaResponse(): ViscaResponse {

    val trimmed = this.dropLastWhile { it == "00".toByte() }.toByteArray()
    val hexString = trimmed.toHexString()
    val status = when (true) {
        MessageLengthError.hexCodesEquals(hexString) -> ViscaResponse.ViscaResponseStatus.Error.MessageLengthError
        SyntaxError.hexCodesEquals(hexString) -> ViscaResponse.ViscaResponseStatus.Error.SyntaxError
        CommandBufferFull.hexCodesEquals(hexString) -> ViscaResponse.ViscaResponseStatus.Error.CommandBufferFull
        CommandCanceled.hexCodesEquals(hexString) -> ViscaResponse.ViscaResponseStatus.Error.CommandCanceled
        NoSocket.hexCodesEquals(hexString) -> ViscaResponse.ViscaResponseStatus.Error.NoSocket
        CommandNotExecutable.hexCodesEquals(hexString) -> ViscaResponse.ViscaResponseStatus.Error.CommandNotExecutable
        ACK.hexCodesEquals(hexString) -> ViscaResponse.ViscaResponseStatus.SuccessResponse.Empty.ACK
        Completion.hexCodesEquals(hexString) -> {
            if (hexString.length == 6) {
                ViscaResponse.ViscaResponseStatus.SuccessResponse.Empty.EmptyCompletion
            } else {
                ViscaResponse.ViscaResponseStatus.SuccessResponse.Body.ArguedCompletion(
                    hexString.drop(4).dropLast(2)
                )
            }
        }

        else -> throw IllegalStateException("Unknown response has been accepted")
    }

    return ViscaResponse(status, trimmed.toHexString())
}