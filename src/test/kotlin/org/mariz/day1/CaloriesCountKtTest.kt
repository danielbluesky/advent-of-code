package org.mariz.day1

import caloriesTopThree
import caloriesVar1
import caloriesVar2
import org.junit.jupiter.api.Test

internal class CaloriesCountKtTest {

    @Test
    fun caloriesVar1Test() {
        caloriesVar1("src/main/resources/input-day-1.txt")
    }

    @Test
    fun caloriesVar2Test() {
        caloriesVar2("src/main/resources/input-day-1.txt")
    }

    @Test
    fun caloriesTopThreeTest() {
        caloriesTopThree("src/main/resources/input-day-1.txt")
    }
}
