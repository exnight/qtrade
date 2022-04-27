package com.exnight.backtest.util;


import static java.lang.Math.abs;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {

	public static final double PRECISION = 1e-8;

	public static boolean isZero(double value) {
		return abs(value) < PRECISION;
	}

	public static double roundDecimal(double decimal, int decimalPlaces) {
		return BigDecimal.valueOf(decimal)
				.setScale(decimalPlaces, RoundingMode.HALF_UP)
				.doubleValue();
	}

	private MathUtil() {
	}

}
