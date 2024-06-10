package ru.linew.visca.api.configurator

import kotlinx.coroutines.runBlocking
import ru.linew.visca.api.ViscaCamera
import ru.linew.visca.api.queries.command.ViscaCommand

fun main() {
    val values = listOf(
        "0000010f",
        "0000010e",
        "0000010d",
        "0000010c",
        "0000010b",
        "0000010a",
        "00000109",
        "00000108",
        "00000107",
        "00000106",
        "00000105",
        "00000104",
        "00000103",
        "00000102",
        "00000101",
        "00000100",
        "0000000f",
        "0000000e",
        "0000000d",
        "0000000c",
        "0000000b",
        "0000000a",
        "00000009",
        "00000008",
        "00000007",
        "00000006",
        "00000005",
        "00000004",
        "00000003",
        "00000002",
        "00000001",
        "00000000"
    )
    val camera = ViscaCamera("192.168.191.13", 1259)
    runBlocking {
        camera.connect()
        camera.sendQuery(object : ViscaCommand{
            override fun getCommand(): String = "040D02"
        })
    }
}