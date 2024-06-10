package ru.linew.visca.api.queries.command.pantilt.drive

private const val PanTiltDriveStopCommandSuffix = "03 03"

class PanTiltDriveStop(panSpeed: Int, tiltSpeed: Int) : InfDriveCommand(panSpeed, tiltSpeed){

    override fun getDriveCommandSuffix(): String = PanTiltDriveStopCommandSuffix

}