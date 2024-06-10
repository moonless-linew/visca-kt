package ru.linew.visca.api

import ru.linew.visca.api.utils.CommandArgumentMark
import ru.linew.visca.api.utils.DefaultAddress
import ru.linew.visca.api.utils.DefaultMask
import ru.linew.visca.api.utils.deleteSpaces

private const val AddressMaskAwareCommandTemplate = "$DefaultAddress $CommandArgumentMark $DefaultMask"

@OptIn(ExperimentalStdlibApi::class)
interface AddressMaskAwareViscaQuery : ViscaQuery {

    override fun buildBytes(): ByteArray {
        return AddressMaskAwareCommandTemplate
            .replaceFirst(CommandArgumentMark, getQuery())
            .deleteSpaces()
            .hexToByteArray()
    }

    fun getQuery(): String

}