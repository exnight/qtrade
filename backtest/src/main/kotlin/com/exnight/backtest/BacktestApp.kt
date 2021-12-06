package com.exnight.backtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BacktestApp

fun main(args: Array<String>) {
    runApplication<BacktestApp>(*args)
}
