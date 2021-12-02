package backtest.metrics

interface MetricsInterface {

    data class StandardMetrics(val sharpe: Double, val maxDrawDown: Double)

    /**
     * Calculate the annualized Sharpe ratio and maximum drawdown with a net asset value series
     */
    fun calculateStandardMetrics(netAssetValue: DoubleArray): StandardMetrics

    /**
     * Calculate the Sharpe ratio with a return series of equal time intervals
     */
    fun calculateSharpe(equalIntervalReturns: DoubleArray): Double

    /**
     * Calculate the maximum drawdown of a return series
     */
    fun calculateMaxDrawDown(returns: DoubleArray): Double

}
