package ru.linew.visca.api.responses.success.empty

import ru.linew.visca.api.responses.ViscaResponseTemplateMatcher

private const val ACKRegexTemplate = "4."

object ACK: ViscaResponseTemplateMatcher() {

    override fun getResponse(): String = ACKRegexTemplate
}