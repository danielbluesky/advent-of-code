package org.mariz.day1

import caloriesTopThree
import caloriesSolution1
import caloriesSolution2
import org.junit.jupiter.api.Test

internal class CalorieCountingKtTest {

    @Test
    fun caloriesVar1Test() {
        caloriesSolution1("src/main/resources/input-day-1.txt")
    }

    @Test
    fun caloriesVar2Test() {
        caloriesSolution2("src/main/resources/input-day-1.txt")
    }

    @Test
    fun caloriesTopThreeTest() {
        caloriesTopThree("src/main/resources/input-day-1.txt")
    }
}
