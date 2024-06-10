package ru.linew.visca.api.queries.command.color.gain.rgain

import ru.linew.visca.api.queries.command.color.ColorCommand

class ColorRedGainUp : ColorCommand {

    override fun getColorCommand(): String = "03 02"
}