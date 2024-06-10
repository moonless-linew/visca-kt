package ru.linew.visca.api.queries.command.exposure.iris

class IrisDown : RelativeIrisCommand() {

    override fun getRelativeIrisCommand(): String = "03"

}