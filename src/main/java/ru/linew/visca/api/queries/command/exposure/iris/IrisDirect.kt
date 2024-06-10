package ru.linew.visca.api.queries.command.exposure.iris

import ru.linew.visca.api.queries.command.exposure.ExposureCommand
import ru.linew.visca.api.utils.ParamMark

private const val IrisCommandTemplate = "4B $ParamMark"
class IrisDirect(private val hexValue: String) : ExposureCommand() {
    override fun getExposureCommand(): String {
        return IrisCommandTemplate.replaceFirst(ParamMark, hexValue)
    }
}