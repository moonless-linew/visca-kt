package ru.linew.visca.api.queries.command.exposure.shutter

import ru.linew.visca.api.queries.command.exposure.ExposureCommand
import ru.linew.visca.api.utils.ParamMark

private const val ShutterCommandTemplate = "4A $ParamMark"
class ShutterDirect(private val hexValue: String) : ExposureCommand() {
    override fun getExposureCommand(): String {
        return ShutterCommandTemplate.replaceFirst(ParamMark, hexValue)
    }
}