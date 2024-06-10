package ru.linew.visca.api.queries.command.preset

private const val PresetRecallCommandTemplate = "02"

class PresetRecall(presetNumber: Int) : CommonPresetCommand(presetNumber) {

    override fun getPresetCommand(): String = PresetRecallCommandTemplate

}
