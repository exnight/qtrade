package com.exnight.backtest.util

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

class Math {

    companion object {

        const val PRECISION = 1e-8

        fun isZero(double: Double): Boolean {
            return abs(double) < PRECISION
        }

        fun roundDecimal(decimal: Double, decimalPlaces: Int): Double {
            return BigDecimal.valueOf(decimal).setScale(decimalPlaces, RoundingMode.HALF_UP)
                .toDouble()
        }

    }

}
