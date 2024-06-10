package ru.linew.visca.api.queries.command.color.whitebalance

import ru.linew.visca.api.queries.command.color.ColorCommand
import ru.linew.visca.api.utils.ParamMark

private const val ColorWhiteBalanceModeTemplate = "35 0$ParamMark"

class ColorWhiteBalanceMode(private val mode: WhiteBalanceMode) : ColorCommand {
    override fun getColorCommand(): String {
        val paramValue = when (mode) {
            WhiteBalanceMode.AUTO -> 0
            WhiteBalanceMode.INDOOR -> 1
            WhiteBalanceMode.OUTDOOR -> 2
            WhiteBalanceMode.ONE_PUSH -> 3
            WhiteBalanceMode.AUTO2 -> 4
            WhiteBalanceMode.MANUAL -> 5
        }
        return ColorWhiteBalanceModeTemplate
            .replaceFirst(ParamMark, paramValue.toString())
    }

    enum class WhiteBalanceMode {
        AUTO, INDOOR, OUTDOOR, AUTO2, ONE_PUSH, MANUAL
    }
}