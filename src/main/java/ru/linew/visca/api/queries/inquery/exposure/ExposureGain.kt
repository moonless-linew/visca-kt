package ru.linew.visca.api.queries.inquery.exposure

import ru.linew.visca.api.queries.inquery.ViscaInquery

private const val ExposureGainInquery = "04 4C"

object ExposureGain : ViscaInquery {

    override fun getInquery(): String = ExposureGainInquery

}