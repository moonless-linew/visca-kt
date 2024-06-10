package ru.linew.visca.api.queries.command.exposure.gain

import ru.linew.visca.api.queries.command.exposure.ExposureCommand
import ru.linew.visca.api.utils.CommandArgumentMark

private const val RelativeGainCommandTemplate = "0C $CommandArgumentMark"

abstract class RelativeGainCommand protected constructor() : ExposureCommand() {

    override fun getExposureCommand(): String {
        return RelativeGainCommandTemplate
            .replaceFirst(CommandArgumentMark, getRelativeGainCommand())
    }

    abstract fun getRelativeGainCommand(): String

}