package ru.linew.visca.api.queries.command.pantilt.drive

import ru.linew.visca.api.queries.command.ViscaCommand
import ru.linew.visca.api.utils.CommandArgumentMark
import ru.linew.visca.api.utils.PanSpeedArgumentMark
import ru.linew.visca.api.utils.TiltSpeedArgumentMark

private const val DriveCommandTemplate = "06 $CommandArgumentMark"

abstract class DriveCommand protected constructor(
    private val panSpeed: Int,
    private val tiltSpeed: Int
) : ViscaCommand {

    override fun getCommand(): String {
        return DriveCommandTemplate
            .replaceFirst(CommandArgumentMark, getDriveCommand())
            .replaceFirst(PanSpeedArgumentMark, "%02d".format(panSpeed))
            .replaceFirst(TiltSpeedArgumentMark, "%02d".format(tiltSpeed))
    }

    abstract fun getDriveCommand(): String

}