package ru.linew.visca.api.queries.command.color.gain

import ru.linew.visca.api.queries.command.color.ColorCommand
import ru.linew.visca.api.utils.CommandArgumentMark
import ru.linew.visca.api.utils.ParamMark

private const val DirectGainCommandTemplate = "$CommandArgumentMark 00 00 0$ParamMark 0$ParamMark"

abstract class DirectGainCommand protected constructor(private val level: Int) : ColorCommand {

    init {
        require(level in -128..127)
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun getColorCommand(): String {
        val hexStr = (level + 128).toHexString()
        return DirectGainCommandTemplate
            .replaceFirst(CommandArgumentMark, getDirectGainCommand())
            .replaceFirst(ParamMark, hexStr.first().toString())
            .replaceFirst(ParamMark, hexStr.last().toString())
    }

    abstract fun getDirectGainCommand(): String
}