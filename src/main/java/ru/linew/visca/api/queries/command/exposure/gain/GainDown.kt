package ru.linew.visca.api.queries.command.exposure.gain

class GainDown : RelativeGainCommand() {

    override fun getRelativeGainCommand(): String = "03"

}