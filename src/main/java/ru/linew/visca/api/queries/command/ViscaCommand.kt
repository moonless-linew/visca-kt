package ru.linew.visca.api.queries.command

import ru.linew.visca.api.AddressMaskAwareViscaQuery
import ru.linew.visca.api.utils.CommandArgumentMark

private const val CommandTemplate = "01 $CommandArgumentMark"

interface ViscaCommand : AddressMaskAwareViscaQuery {

    override fun getQuery(): String {
        return CommandTemplate.replaceFirst(CommandArgumentMark, getCommand())
    }

    fun getCommand(): String

}