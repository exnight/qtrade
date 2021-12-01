package backtest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ApplicationTest {

    @Autowired
    private val controller: Controller? = null

    @Test
    fun runApp_shouldLoadController() {
        Assertions.assertNotNull(controller)
    }

}
