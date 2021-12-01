package backtest.metrics

import org.springframework.stereotype.Service
import kotlin.math.min
import kotlin.math.pow
import backtest.metrics.MetricsInterface.StandardMetrics
import backtest.util.Math.Companion.isZero

@Service
class MetricService : MetricsInterface {

    override fun getStandardMetrics(): StandardMetrics {
        return StandardMetrics(0.67, 0.29)
    }

    override fun calculateSharpe(equalIntervalReturns: DoubleArray): Double {
        val numberOfReturns = equalIntervalReturns.size
        if (numberOfReturns == 0) return 0.0

        val mean = equalIntervalReturns.sum() / numberOfReturns
        var standardDeviation = 0.0

        for (ret in equalIntervalReturns) {
            standardDeviation += (ret - mean).pow(2.0)
        }
        standardDeviation /= numberOfReturns
        standardDeviation = standardDeviation.pow(0.5)

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

    private fun updateMaxDrawDown(maxDrawDown: Double, peak: Double, trough: Double): Double {
        return min(maxDrawDown, (trough - peak) / peak)
    }

}
