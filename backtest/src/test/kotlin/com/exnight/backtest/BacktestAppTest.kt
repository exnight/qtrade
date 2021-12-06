package com.exnight.backtest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BacktestAppTest {

    @Autowired
    private val defaultController: DefaultController? = null

    @Test
    fun runApp_shouldLoadController() {
        Assertions.assertNotNull(defaultController)
    }

}
