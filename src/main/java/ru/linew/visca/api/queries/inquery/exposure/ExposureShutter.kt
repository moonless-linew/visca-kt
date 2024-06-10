package ru.linew.visca.api.queries.inquery.exposure

import ru.linew.visca.api.queries.inquery.ViscaInquery

private const val ExposureShutterInquery = "04 4A"
object ExposureShutter : ViscaInquery {

    override fun getInquery(): String = ExposureShutterInquery

}