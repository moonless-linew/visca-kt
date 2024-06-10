package ru.linew.visca.api.queries.command.pantilt.drive

import ru.linew.visca.api.utils.AngleToHexCoefficient
import ru.linew.visca.api.utils.MaxPanAngle
import ru.linew.visca.api.utils.MaxTiltAngle
import ru.linew.visca.api.utils.MinPanAngle
import ru.linew.visca.api.utils.MinTiltAngle
import ru.linew.visca.api.utils.PanPositionMark
import ru.linew.visca.api.utils.PanSpeedArgumentMark
import ru.linew.visca.api.utils.TiltPositionMark
import ru.linew.visca.api.utils.TiltSpeedArgumentMark
import kotlin.math.roundToInt

private const val PanTiltDriveAbsoluteCommand =
    ("02 $PanSpeedArgumentMark $TiltSpeedArgumentMark 0$PanPositionMark 0$PanPositionMark" +
            " 0$PanPositionMark 0$PanPositionMark 0$TiltPositionMark 0$TiltPositionMark" +
            " 0$TiltPositionMark 0$TiltPositionMark")

@OptIn(ExperimentalStdlibApi::class)
class PanTiltDriveAbsolute(
    private val panAngle: Float,
    private val tiltAngle: Float,
    panSpeed: Int,
    tiltSpeed: Int
) : DriveCommand(panSpeed, tiltSpeed) {

    init {
        require(panAngle in MinPanAngle..MaxPanAngle && tiltAngle in MinTiltAngle..MaxTiltAngle)
    }

    override fun getDriveCommand() = getHexStringForSending()

    private fun getHexStringForSending(): String {
        var template = PanTiltDriveAbsoluteCommand
        (panAngle * AngleToHexCoefficient)
            .roundToInt()
            .toHexString()
            .takeLast(4)
            .forEach { template = template.replaceFirst(PanPositionMark, it.toString()) }
        (tiltAngle * AngleToHexCoefficient)
            .roundToInt()
            .toHexString()
            .takeLast(4)
            .forEach { template = template.replaceFirst(TiltPositionMark, it.toString()) }
        return template
    }
}