package ru.linew.visca.api.utils

const val PanSpeedArgumentMark = "PP"
const val TiltSpeedArgumentMark = "TT"
const val CommandArgumentMark = "CCCC"

const val PanPositionMark = "P1"
const val TiltPositionMark = "T1"

const val ParamMark = "PARAM"

const val DefaultAddress = "81"
const val DefaultMask = "FF"

const val MinPanAngle = -360f
const val MaxPanAngle = 360f
const val MinTiltAngle = -360f
const val MaxTiltAngle = 360f

const val AngleToHexCoefficient = 14.4f

fun String.deleteSpaces(): String = this.replace(" ", "")