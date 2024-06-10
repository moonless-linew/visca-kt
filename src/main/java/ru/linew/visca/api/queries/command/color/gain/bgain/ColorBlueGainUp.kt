package ru.linew.visca.api.queries.command.color.gain.bgain

import ru.linew.visca.api.queries.command.color.ColorCommand

class ColorBlueGainUp : ColorCommand {

    override fun getColorCommand(): String = "04 02"
}