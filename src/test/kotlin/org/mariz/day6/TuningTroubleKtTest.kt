package org.mariz.day6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TuningTroubleKtTest {

    @Test
    fun tuningTroubleResultPart1() {
        println(tuningTrouble("src/main/resources/input-day-6.txt", 4))
    }

    @Test
    fun tuningTroubleResultPart2() {
        println(tuningTrouble("src/main/resources/input-day-6.txt", 14))
    }

    @Test
    fun tuningTroubleTestPart1() {
        val result = tuningTrouble("src/test/kotlin/org/mariz/day6/input-test-1.txt", 4)
        assertEquals(11, result)
    }

    @Test
    fun tuningTroubleTestPart2() {
        val result = tuningTrouble("src/test/kotlin/org/mariz/day6/input-test-1.txt", 14)
        assertEquals(26, result)
    }
}
