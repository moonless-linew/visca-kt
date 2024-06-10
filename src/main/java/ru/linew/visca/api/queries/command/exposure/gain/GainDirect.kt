package ru.linew.visca.api.queries.command.exposure.gain

import ru.linew.visca.api.queries.command.exposure.ExposureCommand
import ru.linew.visca.api.utils.ParamMark

//SMTAVS USE D!!!
private const val GainCommandTemplate = "4C $ParamMark"

class GainDirect(private val hexValue: String) : ExposureCommand() {
    override fun getExposureCommand(): String {
        return GainCommandTemplate
            .replaceFirst(ParamMark, hexValue)
    }
}