package ru.linew.visca.api.queries.command.exposure.gain

class GainReset  : RelativeGainCommand() {

    override fun getRelativeGainCommand(): String = "00"

}