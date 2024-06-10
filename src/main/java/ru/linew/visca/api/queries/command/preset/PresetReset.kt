package ru.linew.visca.api.queries.command.preset

private const val PresetResetCommandTemplate = "00"

class PresetReset(presetNumber: Int) : CommonPresetCommand(presetNumber) {

    override fun getPresetCommand(): String = PresetResetCommandTemplate

}
