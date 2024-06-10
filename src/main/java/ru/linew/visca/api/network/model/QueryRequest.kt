package ru.linew.visca.api.network.model

import ru.linew.visca.api.ViscaQuery

data class QueryRequest(
    val transactionId: String,
    val query: ViscaQuery,
    val repeat: Boolean
)
