package org.mariz.day2

import org.junit.jupiter.api.Test
import playPart1
import playPart2

internal class CaloriesCountKtTest {

    @Test
    fun RockPaperScissorsTestPart1() {
        println(playPart1("src/main/resources/input-day-2.txt"))
    }

    @Test
    fun RockPaperScissorsTestPart2() {
        println(playPart2("src/main/resources/input-day-2.txt"))
    }
}