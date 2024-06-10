package ru.linew.visca.api.queries.command.exposure.shutter

import ru.linew.visca.api.queries.command.exposure.ExposureCommand
import ru.linew.visca.api.utils.CommandArgumentMark

private const val RelativeShutterCommandTemplate = "0A $CommandArgumentMark"

abstract class RelativeShutterCommand protected constructor() : ExposureCommand() {

    override fun getExposureCommand(): String {
        return RelativeShutterCommandTemplate
            .replaceFirst(CommandArgumentMark, getRelativeShutterCommand())
    }

    abstract fun getRelativeShutterCommand(): String

}