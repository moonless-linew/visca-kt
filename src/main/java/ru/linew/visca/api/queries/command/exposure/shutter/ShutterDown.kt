package ru.linew.visca.api.queries.command.exposure.shutter

class ShutterDown : RelativeShutterCommand() {

    override fun getRelativeShutterCommand(): String = "03"

}