package backtest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom
import backtest.metrics.MetricService
import backtest.metrics.MetricsInterface.StandardMetrics
import backtest.metrics.MetricsRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class DefaultController {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var metricService: MetricService

    data class StockPrice(val symbol: String, val price: Double, val time: LocalDateTime)

    @GetMapping("/")
    fun tempRedirectToSwaggerUI(response: ServerHttpResponse): Mono<Void> {
        response.statusCode = HttpStatus.PERMANENT_REDIRECT
        response.headers.location = URI.create("/swagger-ui/")
        return response.setComplete()
    }

    @PostMapping(path = ["/metrics"])
    fun computeStandardMetrics(@RequestBody metricsRequest: MetricsRequest): Mono<StandardMetrics> {
        logger.info("computing standard metrics")
        return Mono.just(metricService.calculateStandardMetrics(metricsRequest.netAssetValues))
    }

    @GetMapping(path = ["/stock/{symbol}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun prices(@PathVariable symbol: String): Flux<StockPrice> {
        return Flux.interval(Duration.ofSeconds(1)).take(5)
            .map { StockPrice(symbol, randomStockPrice(), LocalDateTime.now()) }
    }

    private fun randomStockPrice(): Double {
        return ThreadLocalRandom.current().nextDouble(100.0)
    }

}
