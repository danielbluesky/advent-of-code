package org.mariz.day6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TuningTroubleKtTest {

    @Test
    fun tuningTroubleTest() {
        println(tuningTrouble("src/main/resources/input-day-6.txt", 4))
    }

    @Test
    fun tuningTroubleTest1() {
        val result = tuningTrouble("src/test/kotlin/org/mariz/day6/input-test-1.txt", 4)
        assertEquals(11, result)
    }
}
