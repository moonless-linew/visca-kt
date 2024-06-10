package ru.linew.visca.api.configurator

import ru.linew.visca.api.configurator.configurators.GenericConfigurator
import ru.linew.visca.api.configurator.model.AvailableValuesConfig
import ru.linew.visca.api.network.ViscaQueueHandler
import ru.linew.visca.api.queries.command.color.gain.bgain.ColorBlueDirectRawValue
import ru.linew.visca.api.queries.command.color.gain.bgain.ColorBlueGainDown
import ru.linew.visca.api.queries.command.color.gain.bgain.ColorBlueGainUp
import ru.linew.visca.api.queries.command.color.gain.rgain.ColorRedDirectRawValue
import ru.linew.visca.api.queries.command.color.gain.rgain.ColorRedGainDown
import ru.linew.visca.api.queries.command.color.gain.rgain.ColorRedGainUp
import ru.linew.visca.api.queries.command.exposure.gain.GainDirect
import ru.linew.visca.api.queries.command.exposure.gain.GainDown
import ru.linew.visca.api.queries.command.exposure.gain.GainUp
import ru.linew.visca.api.queries.command.exposure.iris.IrisDirect
import ru.linew.visca.api.queries.command.exposure.iris.IrisDown
import ru.linew.visca.api.queries.command.exposure.iris.IrisUp
import ru.linew.visca.api.queries.command.exposure.shutter.ShutterDirect
import ru.linew.visca.api.queries.command.exposure.shutter.ShutterDown
import ru.linew.visca.api.queries.command.exposure.shutter.ShutterUp
import ru.linew.visca.api.queries.inquery.exposure.ExposureGain
import ru.linew.visca.api.queries.inquery.exposure.ExposureIris
import ru.linew.visca.api.queries.inquery.exposure.ExposureShutter
import ru.linew.visca.api.queries.inquery.whitebalance.WhiteBalanceBGain
import ru.linew.visca.api.queries.inquery.whitebalance.WhiteBalanceRGain

class ViscaCameraConfigurator(
    private val queueHandler: ViscaQueueHandler
) {
    suspend fun configure(): AvailableValuesConfig {
        val rGainConfigurator = GenericConfigurator(
            queueHandler = queueHandler,
            upCommand = ColorRedGainUp(),
            downCommand = ColorRedGainDown(),
            inqueryCommand = WhiteBalanceRGain,
            setOriginalValueCallback = { queueHandler.sendQueryWithResult(ColorRedDirectRawValue(it)) }
        )
        val bGainConfigurator = GenericConfigurator(
            queueHandler = queueHandler,
            upCommand = ColorBlueGainUp(),
            downCommand = ColorBlueGainDown(),
            inqueryCommand = WhiteBalanceBGain,
            setOriginalValueCallback = { queueHandler.sendQueryWithResult(ColorBlueDirectRawValue(it)) }
        )
        val gainConfigurator = GenericConfigurator(
            queueHandler = queueHandler,
            upCommand = GainUp(),
            downCommand = GainDown(),
            inqueryCommand = ExposureGain,
            setOriginalValueCallback = {
                val result = queueHandler.sendQueryWithResult(GainDirect(it))
            }
        )
        val shutterConfigurator = GenericConfigurator(
            queueHandler = queueHandler,
            upCommand = ShutterUp(),
            downCommand = ShutterDown(),
            inqueryCommand = ExposureShutter,
            setOriginalValueCallback = { queueHandler.sendQueryWithResult(ShutterDirect(it)) }
        )
        val irisConfigurator = GenericConfigurator(
            queueHandler = queueHandler,
            upCommand = IrisUp(),
            downCommand = IrisDown(),
            inqueryCommand = ExposureIris,
            setOriginalValueCallback = { queueHandler.sendQueryWithResult(IrisDirect(it)) }
        )

        return AvailableValuesConfig(
            availableGainValues = gainConfigurator.getAvailableValues().reversed(),
            availableShutterValues = shutterConfigurator.getAvailableValues().reversed(),
            availableIrisValues = irisConfigurator.getAvailableValues().reversed(),
            availableRGainValues = rGainConfigurator.getAvailableValues().reversed(),
            availableBGainValues = bGainConfigurator.getAvailableValues().reversed(),
            brokenGain = false
        )
    }
}