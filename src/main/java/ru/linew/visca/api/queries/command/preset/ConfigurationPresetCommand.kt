package ru.linew.visca.api.queries.command.preset

import ru.linew.visca.api.queries.command.ViscaCommand
import ru.linew.visca.api.utils.CommandArgumentMark

private const val ConfigurationPresetCommandTemplate = "7E 04 $CommandArgumentMark"

abstract class ConfigurationPresetCommand : ViscaCommand {

    override fun getCommand(): String = ConfigurationPresetCommandTemplate
        .replaceFirst(CommandArgumentMark, getConfigurationPresetCommand())

    abstract fun getConfigurationPresetCommand(): String

}