package ru.linew.visca.api.responses.success.empty

import ru.linew.visca.api.responses.ViscaResponseTemplateMatcher

private const val CompletionRegexTemplate = "5."

object Completion : ViscaResponseTemplateMatcher() {

    override fun getResponse(): String = CompletionRegexTemplate
}