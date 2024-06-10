package ru.linew.visca.api.queries.inquery.whitebalance

import ru.linew.visca.api.queries.inquery.ViscaInquery

object WhiteBalanceRGain: ViscaInquery {

    override fun getInquery(): String = "04 43"
}