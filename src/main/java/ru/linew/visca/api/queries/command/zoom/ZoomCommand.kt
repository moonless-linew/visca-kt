package ru.linew.visca.api.queries.command.zoom

import ru.linew.visca.api.AddressMaskAwareViscaQuery
import ru.linew.visca.api.utils.CommandArgumentMark

private const val ZoomCommandTemplate = "01 04 $CommandArgumentMark"

abstract class ZoomCommand: AddressMaskAwareViscaQuery {

    override fun getQuery(): String {
        return ZoomCommandTemplate.replaceFirst(CommandArgumentMark, getZoomCommand())
    }

    abstract fun getZoomCommand(): String

}