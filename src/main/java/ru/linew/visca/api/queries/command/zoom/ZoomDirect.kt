package ru.linew.visca.api.queries.command.zoom

import ru.linew.visca.api.utils.ParamMark

private const val ZoomDirectCommand = "47 0$ParamMark 0$ParamMark 0$ParamMark 0$ParamMark"

class ZoomDirect(private val zoomValue: Int, private val cameraType: ZoomCameraType) :
    ZoomCommand() {
    override fun getZoomCommand(): String {
        val byteRepresentation = when (cameraType) {
            ZoomCameraType.X240 -> when (zoomValue) {
                1 -> "0000"
                2 -> "0DC1"
                3 -> "186C"
                4 -> "2015"
                5 -> "2594"
                6 -> "29B7"
                7 -> "2CFB"
                8 -> "2FB0"
                9 -> "320C"
                10 -> "342D"
                11 -> "3608"
                12 -> "37AA"
                13 -> "391C"
                14 -> "3A66"
                15 -> "3B90"
                16 -> "3C9C"
                17 -> "3D91"
                18 -> "3E72"
                19 -> "3F40"
                20 -> "4000"
                30 -> "5556"
                40 -> "6000"
                60 -> "6AAB"
                80 -> "7000"
                100 -> "7334"
                120 -> "7556"
                140 -> "76DC"
                160 -> "7800"
                180 -> "78E4"
                200 -> "799A"
                220 -> "7A2F"
                240 -> "7AC0"
                else -> throw IllegalArgumentException()
            }

            ZoomCameraType.X12 -> when (zoomValue) {
                1 -> "0000"
                2 -> "0FB4"
                3 -> "1BF0"
                4 -> "24C5"
                5 -> "2B1E"
                6 -> "2FE4"
                7 -> "33A9"
                8 -> "36C9"
                9 -> "3983"
                10 -> "2BF7"
                11 -> "3E1C"
                12 -> "4000"
                else -> throw IllegalArgumentException()
            }
        }
        var template = ZoomDirectCommand
        byteRepresentation.forEach {
            template = template.replaceFirst(ParamMark, it.toString())
        }
        return template
    }
}

enum class ZoomCameraType {
    X240, X12
}

