package com.exnight.backtest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import reactor.core.publisher.Flux;

@RestController
public class Controller {

    @Getter
    static class StockPrice {

        private String symbol;
        private Double price;
        private LocalDateTime time;

        StockPrice(String symbol, Double price, LocalDateTime time) {
            this.symbol = symbol;
            this.price = price;
            this.time = time; // s
        }

    }

    @GetMapping("/")
    public String hello() {
        return "Hello world! Maybe return html and JS here...";
    }

    @RequestMapping(value = "/stock/{symbol}", method = RequestMethod.GET,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<StockPrice> prices(@PathVariable String symbol) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(n -> new StockPrice(symbol, randomStockPrice(), LocalDateTime.now()));
    }

    private Double randomStockPrice() {
        return ThreadLocalRandom.current().nextDouble(100.0);
    }

}
