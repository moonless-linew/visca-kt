package ru.linew.visca.api.responses.error

private const val CommandBufferFullResponseTemplate = "03"

object CommandBufferFull : ViscaError() {

    override fun getError(): String = CommandBufferFullResponseTemplate
}