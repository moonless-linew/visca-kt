package ru.linew.visca.api.queries.command.preset

private const val PresetSetCommandTemplate = "01"

class PresetSet(presetNumber: Int) : CommonPresetCommand(presetNumber) {

    override fun getPresetCommand(): String = PresetSetCommandTemplate

}
