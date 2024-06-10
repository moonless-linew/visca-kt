package ru.linew.visca.api.queries.command.exposure.shutter

class ShutterUp : RelativeShutterCommand() {

    override fun getRelativeShutterCommand(): String = "02"

}