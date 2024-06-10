package ru.linew.visca.api.configurator.model

data class AvailableValuesConfig(
    val availableRGainValues: List<String>?,
    val availableBGainValues: List<String>?,
    val availableGainValues: List<String>?,
    val availableIrisValues: List<String>?,
    val availableShutterValues: List<String>?,
    val brokenGain: Boolean
    )
