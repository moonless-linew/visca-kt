package ru.linew.visca.api.queries.command.exposure.gain

class GainUp : RelativeGainCommand() {

    override fun getRelativeGainCommand(): String = "02"

}