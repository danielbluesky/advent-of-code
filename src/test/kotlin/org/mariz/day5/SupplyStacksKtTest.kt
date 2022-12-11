package org.mariz.day5

import org.junit.jupiter.api.Test
import supplyStacksPart1
import supplyStacksPart1Solution2
// import supplyStacksPart2

internal class SupplyStacksKtTest {

    @Test
    fun testSupplyStacksPart1() {
        println(supplyStacksPart1("src/main/resources/input-day-5.txt", false))
    }

    @Test
    fun testSupplyStacksPart1Solution2() {
        println(supplyStacksPart1Solution2("src/main/resources/input-day-5.txt"))
    }
}
