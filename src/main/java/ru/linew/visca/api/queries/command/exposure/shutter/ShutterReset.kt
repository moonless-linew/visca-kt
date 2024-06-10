package ru.linew.visca.api.queries.command.exposure.shutter

class ShutterReset : RelativeShutterCommand() {
    override fun getRelativeShutterCommand(): String = "00"

}