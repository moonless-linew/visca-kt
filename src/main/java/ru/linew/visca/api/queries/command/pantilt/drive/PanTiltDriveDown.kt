package ru.linew.visca.api.queries.command.pantilt.drive

private const val PanTiltDriveDownCommandSuffix = "03 02"

class PanTiltDriveDown(panSpeed: Int, tiltSpeed: Int) : InfDriveCommand(panSpeed, tiltSpeed) {

    override fun getDriveCommandSuffix(): String = PanTiltDriveDownCommandSuffix

}