package ru.linew.visca.api.queries.command.zoom

private const val ZoomStopStandardSpeedCommand = "00"

object ZoomStopStandardSpeed: ZoomInfCommandStandardSpeed() {
    override fun getZoomCommandStandardSpeed(): String = ZoomStopStandardSpeedCommand

}