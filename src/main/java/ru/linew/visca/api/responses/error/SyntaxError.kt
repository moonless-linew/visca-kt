package ru.linew.visca.api.responses.error

private const val SyntaxErrorResponseTemplate = "02"

object SyntaxError : ViscaError() {

    override fun getError(): String = SyntaxErrorResponseTemplate

}