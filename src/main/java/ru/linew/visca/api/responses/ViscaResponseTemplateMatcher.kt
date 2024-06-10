package ru.linew.visca.api.responses

import ru.linew.visca.api.utils.CommandArgumentMark
import ru.linew.visca.api.utils.deleteSpaces

private const val ViscaResponseRegexTemplate = "90 $CommandArgumentMark .* FF"

abstract class ViscaResponseTemplateMatcher {

    abstract fun getResponse(): String

    fun hexCodesEquals(other: String): Boolean {
        val thisResponse: String =
            ViscaResponseRegexTemplate
                .replaceFirst(CommandArgumentMark, getResponse())
                .deleteSpaces()
                .lowercase()
        val otherResponse: String = other.lowercase()
        return otherResponse.matches(Regex(thisResponse))
    }
}