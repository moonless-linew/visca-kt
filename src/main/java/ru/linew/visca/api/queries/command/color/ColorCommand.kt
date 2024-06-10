package ru.linew.visca.api.queries.command.color

import ru.linew.visca.api.queries.command.ViscaCommand
import ru.linew.visca.api.utils.CommandArgumentMark

private const val ColorCommandTemplate =
    ("04 $CommandArgumentMark")

interface ColorCommand : ViscaCommand {
    override fun getCommand(): String {
        return ColorCommandTemplate
            .replaceFirst(CommandArgumentMark, getColorCommand())
    }

    fun getColorCommand(): String

}
