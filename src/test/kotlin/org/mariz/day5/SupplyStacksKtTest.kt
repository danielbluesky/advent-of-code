package org.mariz.day5

import doMoveOperations
import org.junit.jupiter.api.Test
import parseMoves
import parseStacks
import supplyStacksPart1
import supplyStacksPart1Solution2
import kotlin.test.assertEquals

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

    @Test
    fun testParseStacks() {
        val input: List<String> = listOf(
            "[H] [M] [N] [Z] [M] [C] [M] [P] [P]",
            "[P]     [L]         [T] [X]        ",
            "[A]                     [A]        "
        )
        val expectedOutput: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf<String>("H, P, A"),
            2 to mutableListOf<String>("M"),
            3 to mutableListOf<String>("N, L"),
            4 to mutableListOf<String>("Z"),
            5 to mutableListOf<String>("M"),
            6 to mutableListOf<String>("C, T"),
            7 to mutableListOf<String>("M, X, A"),
            8 to mutableListOf<String>("P"),
            9 to mutableListOf<String>("P")
        )
        assertEquals(expectedOutput.toString(), parseStacks(input).toString())
    }

    @Test
    fun testParseMoveInstructions() {
        val input: List<String> = listOf(
            "move 8 from 3 to 2",
            "move 13 from 8 to 4",
            "move 1 from 4 to 7"
        )
        val expectedOutput: MutableMap<Int, MutableList<Int>> = mutableMapOf(
            0 to mutableListOf<Int>(8, 3, 2),
            1 to mutableListOf<Int>(13, 8, 4),
            2 to mutableListOf<Int>(1, 4, 7)
        )
        assertEquals(expectedOutput, parseMoves(input))
    }

    @Test
    fun testDoMoveOperations() {
        val stacks: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("A, B, C, D, E, F"),
            2 to mutableListOf("M"),
            3 to mutableListOf("X, Y, Z")
        )
        val inputInstructions: MutableMap<Int, MutableList<Int>> = mutableMapOf(
            1 to mutableListOf(4, 1, 2),
            2 to mutableListOf(5, 2, 3),
            3 to mutableListOf(7, 3, 2)
        )
        val expectedOutput: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("A, B"),
            2 to mutableListOf("M, F, E, D, C, Z, Y"),
            3 to mutableListOf("X")
        )
        // assertEquals(expectedOutput, doMoveOperations(stacks, inputInstructions))
    }
}
