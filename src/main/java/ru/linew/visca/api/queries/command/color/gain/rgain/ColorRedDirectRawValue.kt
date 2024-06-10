package ru.linew.visca.api.queries.command.color.gain.rgain

import ru.linew.visca.api.queries.command.color.ColorCommand

class ColorRedDirectRawValue(private val hexValue: String) : ColorCommand {

    override fun getColorCommand(): String = "43 $hexValue"

}