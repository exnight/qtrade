package com.exnight.backtest.metrics

import com.exnight.backtest.metrics.MetricsInterface.StandardMetrics
import com.exnight.backtest.util.Math.Companion.isZero
import com.exnight.backtest.util.Math.Companion.roundDecimal
import org.springframework.stereotype.Service
import kotlin.math.min
import kotlin.math.pow

@Service
class MetricService : MetricsInterface {

    override fun calculateStandardMetrics(netAssetValue: DoubleArray): StandardMetrics {
        val returns = DoubleArray(netAssetValue.size - 1) { 0.0 }
        for (i in 1 until netAssetValue.size) {
            returns[i - 1] = (netAssetValue[i] / netAssetValue[i - 1]) - 1
        }

        val decimalPlaces = 4
        val annualizedSharpe = roundDecimal(
            calculateSharpe(returns) * 365.25.pow(0.5), decimalPlaces
        )
        val maxDrawDown = roundDecimal(calculateMaxDrawDown(returns), decimalPlaces)

        return StandardMetrics(annualizedSharpe, maxDrawDown)
    }

    override fun calculateSharpe(equalIntervalReturns: DoubleArray): Double {
        if (equalIntervalReturns.isEmpty()) return 0.0

        val mean = calculateMean(equalIntervalReturns)
        val standardDeviation = calculateStandardDeviation(equalIntervalReturns)

        return if (isZero(standardDeviation)) -1.0 else mean / standardDeviation
    }

    override fun calculateMaxDrawDown(returns: DoubleArray): Double {
        if (returns.isEmpty()) return 0.0

        val netValue = DoubleArray(returns.size) { 0.0 }
        netValue[0] = 1 + returns[0]
        var peak = netValue[0]
        var trough = netValue[0]
        var maxDrawDown = updateMaxDrawDown(0.0, peak, trough)

        for (i in 1 until returns.size) {
            val currValue = netValue[i - 1] * (1 + returns[i])
            netValue[i] = currValue
            if (currValue > peak) {
                peak = currValue
                trough = peak
            }
            if (currValue < trough) {
                trough = currValue
                maxDrawDown = updateMaxDrawDown(maxDrawDown, peak, trough)
            }
        }

        return maxDrawDown
    }

    private fun calculateMean(equalIntervalReturns: DoubleArray): Double {
        val numberOfReturns = equalIntervalReturns.size
        return if (numberOfReturns == 0) 0.0 else equalIntervalReturns.sum() / numberOfReturns
    }

    private fun calculateStandardDeviation(equalIntervalReturns: DoubleArray): Double {
        if (equalIntervalReturns.isEmpty()) return 0.0
        return calculateVariance(equalIntervalReturns).pow(0.5)
    }

    private fun calculateVariance(equalIntervalReturns: DoubleArray): Double {
        val mean = calculateMean(equalIntervalReturns)
        return equalIntervalReturns.fold(0.0) { acc, ret ->
            acc + (ret - mean).pow(
                2.0
            ) / equalIntervalReturns.size
        }
    }

    private fun updateMaxDrawDown(
        maxDrawDown: Double, peak: Double, trough: Double
    ): Double {
        return min(maxDrawDown, (trough - peak) / peak)
    }

}
