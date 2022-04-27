package com.exnight.backtest.util;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MathUtilTest {

	@Test
	void precision_shouldReturnTrue() {
		BigDecimal expectedPrecision = BigDecimal.valueOf(1e-8);
		assertEquals(expectedPrecision, BigDecimal.valueOf(MathUtil.PRECISION));
	}

	@Test
	void isZero_shouldReturnTrue() {
		assertTrue(MathUtil.isZero(0.0));
	}

	@Test
	void zero_belowPrecision_shouldReturnFalse() {
		assertTrue(MathUtil.isZero(1e-10));
	}

	@Test
	void zero_abovePrecision_shouldReturnFalse() {
		assertFalse(MathUtil.isZero(1e-6));
	}

	@Test
	void roundDecimal_to4DP_shouldReturn4DP() {
		String expected = "1.2345";
		String actual = String.valueOf(MathUtil.roundDecimal(1.23445, 4));
		assertEquals(expected, actual);
	}

	@Test
	void roundDecimal_to2DP_shouldReturn2DP() {
		String expected = "7.21";
		String actual = String.valueOf(MathUtil.roundDecimal(7.214, 2));
		assertEquals(expected, actual);
	}

}
