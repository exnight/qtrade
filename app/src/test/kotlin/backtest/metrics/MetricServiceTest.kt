package backtest.metrics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import backtest.util.Math

internal class MetricServiceTest {

    private val metricsService: MetricService = MetricService()

    @Test
    fun getStandardMetrics() {
        val metrics = metricsService.getStandardMetrics()
        assertEquals(0.67, metrics.sharpe, Math.PRECISION)
        assertEquals(0.29, metrics.maxDrawDown, Math.PRECISION)
    }

    @Test
    fun calcSharpe_ZeroLengthInput_Zero() {
        val sharpe = metricsService.calculateSharpe(doubleArrayOf())
        assertEquals(0.0, sharpe, Math.PRECISION)
    }

    @Test
    fun calcSharpe_ZeroSD_negative1() {
        val sharpe = metricsService.calculateSharpe(doubleArrayOf(0.1, 0.1, 0.1))
        assertEquals(-1.0, sharpe, Math.PRECISION)
    }

    @Test
    fun calcSharpe_case1_0_1098() {
        val returns = doubleArrayOf(0.1, -0.1, -0.1, 0.15)
        val sharpe = metricsService.calculateSharpe(returns)
        assertEquals(0.10976426, sharpe, Math.PRECISION)
    }

    @Test
    fun calcSharpe_case2_0_3319() {
        val returns = doubleArrayOf(0.02, 0.03, -0.05, 0.05)
        val sharpe = metricsService.calculateSharpe(returns)
        assertEquals(0.33186166, sharpe, Math.PRECISION)
    }

    @Test
    fun calcMDD_noChange_zero() {
        val returns = doubleArrayOf(0.0, 0.0, 0.0, 0.0)
        val maxDrawDown = metricsService.calculateMaxDrawDown(returns)
        assertEquals(0.0, maxDrawDown, Math.PRECISION)
    }

    @Test
    fun calcMDD_ZeroLengthInput_zero() {
        val returns = doubleArrayOf()
        val maxDrawDown = metricsService.calculateMaxDrawDown(returns)
        assertEquals(0.0, maxDrawDown, Math.PRECISION)
    }

    @Test
    fun calcMDD_monotonicIncrease_zero() {
        val returns = doubleArrayOf(0.0, 0.1, 0.0, 0.25, 0.15)
        val maxDrawDown = metricsService.calculateMaxDrawDown(returns)
        assertEquals(0.0, maxDrawDown, Math.PRECISION)
    }

    @Test
    fun calcMDD_oneDecline_20percent() {
        val returns = doubleArrayOf(0.0, 0.0, -0.2, 0.25, 0.1)
        val maxDrawDown = metricsService.calculateMaxDrawDown(returns)
        assertEquals(-0.2, maxDrawDown, Math.PRECISION)
    }

    @Test
    fun calcMDD_twoDeclines_52percent() {
        val returns = doubleArrayOf(0.0, -0.2, 0.0, 0.25, 0.5, -0.2, -0.4, 0.25)
        val maxDrawDown = metricsService.calculateMaxDrawDown(returns)
        assertEquals(-0.52, maxDrawDown, Math.PRECISION)
    }

}
