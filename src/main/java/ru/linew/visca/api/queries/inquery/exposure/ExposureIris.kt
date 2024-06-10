package ru.linew.visca.api.queries.inquery.exposure

import ru.linew.visca.api.queries.inquery.ViscaInquery

private const val ExposureIrisInquery = "04 4B"

object ExposureIris : ViscaInquery {

    override fun getInquery(): String = ExposureIrisInquery

}