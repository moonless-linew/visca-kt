package ru.linew.visca.api.queries.command.pantilt.drive

private const val PanTiltDriveLeftCommandSuffix = "01 03"

class PanTiltDriveLeft(panSpeed: Int, tiltSpeed: Int) : InfDriveCommand(panSpeed, tiltSpeed) {

    override fun getDriveCommandSuffix(): String = PanTiltDriveLeftCommandSuffix

}