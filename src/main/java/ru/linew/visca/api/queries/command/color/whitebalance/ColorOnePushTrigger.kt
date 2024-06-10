package ru.linew.visca.api.queries.command.color.whitebalance

import ru.linew.visca.api.queries.command.color.ColorCommand

private const val ColorWhiteBalanceModeTemplate = "10 05"

object ColorOnePushTrigger : ColorCommand {
    override fun getColorCommand(): String {
        return ColorWhiteBalanceModeTemplate
    }
}