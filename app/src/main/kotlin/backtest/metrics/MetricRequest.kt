package backtest.metrics

import com.fasterxml.jackson.annotation.JsonProperty

data class MetricsRequest(@JsonProperty("net-asset-values") val netAssetValues: DoubleArray) {

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
