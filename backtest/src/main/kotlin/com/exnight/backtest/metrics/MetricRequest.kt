package com.exnight.backtest.metrics

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.server.ServerWebInputException
import java.time.LocalDate

data class MetricsRequest(@JsonProperty("net-asset-values") val netAssetValues: DoubleArray,
                          @JsonProperty("nav-dates") val navDates: List<LocalDate>) {

    fun validate() {
        if (netAssetValues.isEmpty()) {
            throw ServerWebInputException("There is no NAV data")
        }
        if (navDates.isEmpty()) {
            throw ServerWebInputException("There is no NAV dates")
        }
        if (netAssetValues.size != navDates.size) {
            throw ServerWebInputException("The amount of NAV data should match the number of dates")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MetricsRequest

        if (!netAssetValues.contentEquals(other.netAssetValues)) return false

        return true
    }

    override fun hashCode(): Int {
        return netAssetValues.contentHashCode()
    }

}
