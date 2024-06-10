package ru.linew.visca.api.queries.command.pantilt.drive

private const val PanTiltDriveRightCommandSuffix = "02 03"

class PanTiltDriveRight(panSpeed: Int, tiltSpeed: Int) : InfDriveCommand(panSpeed, tiltSpeed) {

    override fun getDriveCommandSuffix(): String = PanTiltDriveRightCommandSuffix

}