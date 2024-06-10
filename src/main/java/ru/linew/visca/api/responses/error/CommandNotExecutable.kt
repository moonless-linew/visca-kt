package ru.linew.visca.api.responses.error

private const val CommandNotExecutableResponseTemplate = "41"

object CommandNotExecutable : ViscaError() {

    override fun getError(): String = CommandNotExecutableResponseTemplate
}