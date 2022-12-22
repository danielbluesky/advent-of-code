package org.mariz.day6

import org.junit.jupiter.api.Test

internal class TuningTroubleKtTest {

    @Test
    fun tuningTroubleResultPart1() {
        println(tuningTrouble("src/main/resources/input-day-6.txt", 4))
    }

    @Test
    fun tuningTroubleResultPart2() {
        println(tuningTrouble("src/main/resources/input-day-6.txt", 14))
    }
}
