package ru.linew.visca.api.queries.command.color.gain.rgain

import ru.linew.visca.api.queries.command.color.ColorCommand

class ColorRedGainReset : ColorCommand {

    override fun getColorCommand(): String = "03 00"
}