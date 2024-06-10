package ru.linew.visca.api.network.model

data class ViscaResponse(
    val status: ViscaResponseStatus,
    val rawMessage: String
) {

    sealed interface ViscaResponseStatus {

        sealed interface SuccessResponse : ViscaResponseStatus {
            sealed interface Empty : SuccessResponse {

                data object ACK : Empty

                data object EmptyCompletion : Empty
            }

            sealed interface Body : SuccessResponse {
                data class ArguedCompletion(val args: String) : Body
            }
        }

        sealed interface Error : ViscaResponseStatus {

            data object MessageLengthError : Error

            data object SyntaxError : Error

            data object CommandBufferFull : Error

            data object CommandCanceled : Error

            data object NoSocket : Error

            data object CommandNotExecutable : Error

            data object TimeoutError: Error

        }
    }
}
