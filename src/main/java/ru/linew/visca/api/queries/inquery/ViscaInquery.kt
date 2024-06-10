package ru.linew.visca.api.queries.inquery

import ru.linew.visca.api.AddressMaskAwareViscaQuery
import ru.linew.visca.api.utils.CommandArgumentMark
import ru.linew.visca.api.utils.deleteSpaces

private const val CommandTemplate = "09 $CommandArgumentMark"

interface ViscaInquery : AddressMaskAwareViscaQuery {

    override fun getQuery(): String {
        return CommandTemplate.replaceFirst(CommandArgumentMark, getInquery()).deleteSpaces()
    }

    fun getInquery(): String

}