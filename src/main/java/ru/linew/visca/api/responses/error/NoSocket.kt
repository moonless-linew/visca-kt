package ru.linew.visca.api.responses.error

private const val NoSocketResponseTemplate = "05"

object NoSocket : ViscaError() {

    override fun getError(): String = NoSocketResponseTemplate
}