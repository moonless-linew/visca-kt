package ru.linew.visca.api.queries.command.preset

import ru.linew.visca.api.utils.ParamMark

private const val PresetModeCommandTemplate = "3D $ParamMark"

class PresetMode(private val mode: Int) : ConfigurationPresetCommand() {

    init {
        require(mode in 1..2)
    }

    override fun getConfigurationPresetCommand(): String = PresetModeCommandTemplate
        .replaceFirst(ParamMark, "%02d".format(mode - 1))

}