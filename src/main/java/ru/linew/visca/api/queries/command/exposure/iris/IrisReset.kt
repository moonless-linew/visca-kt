package ru.linew.visca.api.queries.command.exposure.iris

class IrisReset : RelativeIrisCommand() {

    override fun getRelativeIrisCommand(): String = "00"

}