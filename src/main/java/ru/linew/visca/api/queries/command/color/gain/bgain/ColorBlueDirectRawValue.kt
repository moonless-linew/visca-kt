package ru.linew.visca.api.queries.command.color.gain.bgain

import ru.linew.visca.api.queries.command.color.ColorCommand

class ColorBlueDirectRawValue(private val hexValue: String) : ColorCommand {

    override fun getColorCommand(): String = "44 $hexValue"

}