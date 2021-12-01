package backtest.util

import kotlin.math.abs

class Math {

    companion object {

        const val PRECISION = 1e-8
        fun isZero(double: Double): Boolean {
            return abs(double) < PRECISION
        }

    }

}
