package ru.linew.visca.api.queries.command.pantilt.drive

private const val PanTiltDriveUpCommandSuffix = "03 01"

class PanTiltDriveUp(panSpeed: Int, tiltSpeed: Int) : InfDriveCommand(panSpeed, tiltSpeed) {

    override fun getDriveCommandSuffix(): String = PanTiltDriveUpCommandSuffix

}