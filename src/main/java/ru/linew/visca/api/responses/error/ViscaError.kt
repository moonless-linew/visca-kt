package ru.linew.visca.api.responses.error

import ru.linew.visca.api.responses.ViscaResponseTemplateMatcher
import ru.linew.visca.api.utils.CommandArgumentMark

private const val ErrorTemplate = "6. $CommandArgumentMark"

abstract class ViscaError : ViscaResponseTemplateMatcher() {

    override fun getResponse(): String {
        return ErrorTemplate.replaceFirst(CommandArgumentMark, getError())
    }

    abstract fun getError(): String

}