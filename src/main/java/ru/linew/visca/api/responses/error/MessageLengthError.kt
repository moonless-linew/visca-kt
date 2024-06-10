package ru.linew.visca.api.responses.error

private const val MessageLengthErrorResponseTemplate = "01"

object MessageLengthError : ViscaError() {

    override fun getError(): String = MessageLengthErrorResponseTemplate
}