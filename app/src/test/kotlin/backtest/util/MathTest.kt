package backtest.util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MathTest {

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

