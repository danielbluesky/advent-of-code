package org.mariz.day1

import org.junit.jupiter.api.Test

internal class CalorieCountingKtTest {

    @Test
    fun caloriesVar1Result() {
        caloriesSolution1("src/main/resources/input-day-1.txt")
    }

    @Test
    fun caloriesVar2Result() {
        caloriesSolution2("src/main/resources/input-day-1.txt")
    }

    @Test
    fun caloriesTopThreeResult() {
        caloriesTopThree("src/main/resources/input-day-1.txt")
    }
}
