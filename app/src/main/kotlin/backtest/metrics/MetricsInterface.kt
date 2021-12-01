package backtest.metrics

interface MetricsInterface {

    data class StandardMetrics(val sharpe: Double, val maxDrawDown: Double)

    /**
     * Return the standard metrics
     */
    fun getStandardMetrics(): StandardMetrics

    /**
     * TODO: add documentation
     */
    fun calculateSharpe(equalIntervalReturns: DoubleArray): Double

    /**
     * TODO: add documentation
     */
    fun calculateMaxDrawDown(returns: DoubleArray): Double

}
