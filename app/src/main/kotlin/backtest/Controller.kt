package backtest

import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.PathVariable
import reactor.core.publisher.Flux
import lombok.Getter
import org.springframework.http.MediaType
import java.time.Duration
import java.util.concurrent.ThreadLocalRandom

@RestController
class Controller {
    @Getter
    class StockPrice(
        private val symbol: String,
        private val price: Double,
        private val time: LocalDateTime
    )

    @GetMapping("/")
    fun hello(): String {
        return "Hello world! Maybe return html and JS here..."
    }

    @GetMapping(path = ["/stock/{symbol}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun prices(@PathVariable symbol: String): Flux<StockPrice> {
        return Flux.interval(Duration.ofSeconds(1))
            .map { StockPrice(symbol, randomStockPrice(), LocalDateTime.now()) }
    }

    private fun randomStockPrice(): Double {
        return ThreadLocalRandom.current().nextDouble(100.0)
    }
}
