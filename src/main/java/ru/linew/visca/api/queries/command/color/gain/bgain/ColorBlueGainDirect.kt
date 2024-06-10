package ru.linew.visca.api.queries.command.color.gain.bgain

import ru.linew.visca.api.queries.command.color.gain.DirectGainCommand

private const val ColorBlueGainDirectCommandTemplate = "44"

class ColorBlueGainDirect(level: Int) : DirectGainCommand(level) {

    override fun getDirectGainCommand(): String = ColorBlueGainDirectCommandTemplate

}