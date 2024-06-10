package ru.linew.visca.api.queries.command.exposure.mode

import ru.linew.visca.api.queries.command.exposure.ExposureCommand

class ExposureModeChange(private val mode: ExposureMode) : ExposureCommand() {

    enum class ExposureMode {
        FULL_AUTO, MANUAL, SHUTTER_PRIORITY, IRIS_PRIORITY
    }

    override fun getExposureCommand(): String {
        val postfix = when (mode) {
            ExposureMode.FULL_AUTO -> "0"
            ExposureMode.MANUAL -> "3"
            ExposureMode.SHUTTER_PRIORITY -> "A"
            ExposureMode.IRIS_PRIORITY -> "B"
        }
        return "39 0$postfix"
    }
}