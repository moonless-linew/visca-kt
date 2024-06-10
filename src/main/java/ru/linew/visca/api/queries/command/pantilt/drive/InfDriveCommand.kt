package ru.linew.visca.api.queries.command.pantilt.drive

import ru.linew.visca.api.utils.CommandArgumentMark
import ru.linew.visca.api.utils.PanSpeedArgumentMark
import ru.linew.visca.api.utils.TiltSpeedArgumentMark


private const val InfDriveCommandTemplate =
    "01 $PanSpeedArgumentMark $TiltSpeedArgumentMark $CommandArgumentMark"

abstract class InfDriveCommand protected constructor(panSpeed: Int, tiltSpeed: Int) :
    DriveCommand(panSpeed, tiltSpeed) {

    override fun getDriveCommand(): String {
        return InfDriveCommandTemplate
            .replaceFirst(CommandArgumentMark, getDriveCommandSuffix())
    }

    abstract fun getDriveCommandSuffix(): String

}