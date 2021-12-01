package backtest

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class DefaultController {

    data class StockPrice(
        val symbol: String,
        val price: Double,
        val time: LocalDateTime
    )

    @GetMapping("/")
    fun tempRedirectToSwaggerUI(response: ServerHttpResponse): Mono<Void> {
        response.statusCode = HttpStatus.PERMANENT_REDIRECT
        response.headers.location = URI.create("/swagger-ui/")
        return response.setComplete()
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
