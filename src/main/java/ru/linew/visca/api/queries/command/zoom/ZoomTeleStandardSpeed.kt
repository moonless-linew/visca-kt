package ru.linew.visca.api.queries.command.zoom

private const val ZoomTeleStandardSpeedCommand = "02"

object ZoomTeleStandardSpeed : ZoomInfCommandStandardSpeed() {
    override fun getZoomCommandStandardSpeed(): String = ZoomTeleStandardSpeedCommand

}