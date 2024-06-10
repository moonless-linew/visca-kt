package ru.linew.visca.api.queries.command.zoom

import ru.linew.visca.api.utils.CommandArgumentMark

private const val ZoomCommandStandardSpeedTemplate = "07 $CommandArgumentMark"

abstract class ZoomInfCommandStandardSpeed : ZoomCommand() {

    override fun getZoomCommand(): String {
        return ZoomCommandStandardSpeedTemplate.replaceFirst(
            CommandArgumentMark,
            getZoomCommandStandardSpeed()
        )
    }

    abstract fun getZoomCommandStandardSpeed(): String

}