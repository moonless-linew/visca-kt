package ru.linew.visca.api.queries.inquery.whitebalance

import ru.linew.visca.api.queries.inquery.ViscaInquery

object WhiteBalanceMode: ViscaInquery {

    override fun getInquery(): String = "04 35"

}