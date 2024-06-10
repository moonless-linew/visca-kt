package ru.linew.visca.api.queries.command.preset

import ru.linew.visca.api.queries.command.ViscaCommand
import ru.linew.visca.api.utils.CommandArgumentMark
import ru.linew.visca.api.utils.ParamMark

private const val PresetCommandTemplate = "04 3F $CommandArgumentMark $ParamMark"

abstract class CommonPresetCommand protected constructor(private val presetNumber: Int) :
    ViscaCommand {

    init {
        require(presetNumber in 0..50)
    }

    override fun getCommand(): String {
        return PresetCommandTemplate
            .replaceFirst(CommandArgumentMark, getPresetCommand())
            .replaceFirst(ParamMark, "%02d".format(presetNumber))
    }

    abstract fun getPresetCommand(): String

}
