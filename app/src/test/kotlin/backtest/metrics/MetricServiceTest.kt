package backtest.metrics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MetricServiceTest {

    private val metricsService: MetricService = MetricService()

    @Test
    fun getStandardMetrics() {
        val metrics = metricsService.getStandardMetrics()
        assertEquals(0.67, metrics.sharpe)
        assertEquals(0.29, metrics.maxDrawDown)
    }

    @Test
    fun calcSharpe_ZeroLengthInput_Zero() {
        val sharpe = metricsService.calculateSharpe(doubleArrayOf())
        assertEquals(0.0, sharpe)
    }

    @Test
    fun calcSharpe_ZeroSD_negative1() {
        val sharpe = metricsService.calculateSharpe(doubleArrayOf(0.1, 0.1, 0.1))
        assertEquals(-1.0, sharpe)
    }

    @Test
    fun calcMDD_TODO() {
        TODO()
    }

}
