package ru.linew.visca.api.queries.command.color.gain.rgain

import ru.linew.visca.api.queries.command.color.gain.DirectGainCommand

private const val ColorRedGainDirectCommandTemplate = "43"

class ColorRedGainDirect(level: Int) : DirectGainCommand(level) {

    override fun getDirectGainCommand(): String = ColorRedGainDirectCommandTemplate

}