package ru.linew.visca.api.queries.command.zoom


private const val ZoomWideStandardSpeedCommand = "03"

object ZoomWideStandardSpeed : ZoomInfCommandStandardSpeed() {
    override fun getZoomCommandStandardSpeed(): String = ZoomWideStandardSpeedCommand

}