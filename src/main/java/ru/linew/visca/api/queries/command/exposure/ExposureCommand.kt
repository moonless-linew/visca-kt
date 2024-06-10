package ru.linew.visca.api.queries.command.exposure

import ru.linew.visca.api.queries.command.ViscaCommand
import ru.linew.visca.api.utils.CommandArgumentMark

private const val ExposureCommandTemplate = "04 $CommandArgumentMark"
abstract class ExposureCommand protected constructor() : ViscaCommand {

    override fun getCommand(): String {
        return ExposureCommandTemplate
            .replaceFirst(CommandArgumentMark, getExposureCommand())
    }

    abstract fun getExposureCommand(): String

}