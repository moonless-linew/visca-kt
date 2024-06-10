package ru.linew.visca.api.responses.error

private const val CommandCanceledResponseTemplate = "04"

object CommandCanceled : ViscaError() {

    override fun getError(): String = CommandCanceledResponseTemplate
}