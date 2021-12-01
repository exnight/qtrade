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

}

