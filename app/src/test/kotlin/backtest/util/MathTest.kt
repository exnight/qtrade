package backtest.util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class MathTest {

    @Test
    fun precision_shouldReturnTrue() {
        val expectedPrecision = BigDecimal.valueOf(1e-8)
        assertTrue(expectedPrecision.equals(BigDecimal.valueOf(Math.PRECISION)))
    }

    @Test
    fun zero_shouldReturnTrue() {
        assertTrue(Math.isZero(0.0))
    }

    @Test
    fun zero_belowPrecision_shouldReturnFalse() {
        assertTrue(Math.isZero(1e-10))
    }

    @Test
    fun zero_abovePrecision_shouldReturnFalse() {
        assertFalse(Math.isZero(1e-6))
    }

    @Test
    fun roundDecimal_to4DP_shouldReturn4DP() {
        val expected = "1.2345"
        val actual = Math.roundDecimal(1.23445, 4).toString()
        assertTrue(expected == actual)
    }

    @Test
    fun roundDecimal_to2DP_shouldReturn2DP() {
        val expected = "7.21"
        val actual = Math.roundDecimal(7.214, 2).toString()
        assertTrue(expected == actual)
    }

}

