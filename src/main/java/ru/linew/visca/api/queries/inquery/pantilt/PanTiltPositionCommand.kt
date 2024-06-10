package ru.linew.visca.api.queries.inquery.pantilt

import ru.linew.visca.api.queries.inquery.ViscaInquery

private const val GetPanTiltPositionCommand = "06 12"

object PanTiltPositionCommand : ViscaInquery {

    override fun getInquery(): String = GetPanTiltPositionCommand


}