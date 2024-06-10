package ru.linew.visca.api.queries.command.exposure.iris

import ru.linew.visca.api.queries.command.exposure.ExposureCommand
import ru.linew.visca.api.utils.CommandArgumentMark

private const val RelativeIrisCommandTemplate = "0B $CommandArgumentMark"

abstract class RelativeIrisCommand protected constructor() : ExposureCommand() {

    override fun getExposureCommand(): String {
        return RelativeIrisCommandTemplate
            .replaceFirst(CommandArgumentMark, getRelativeIrisCommand())
    }

    abstract fun getRelativeIrisCommand(): String

}