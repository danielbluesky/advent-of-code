package org.mariz.day5

import CrateMover
import calculateResult
import doMoveOperationsPart2
import org.junit.jupiter.api.Test
import parseMoves
import parseStacks
import supplyStacksPart1
import supplyStacksPart1Solution2
import kotlin.test.assertEquals

internal class SupplyStacksKtTest {
    /*
    @Test
    fun testSupplyStacksPart1() {
        println(supplyStacksPart1("src/main/resources/input-day-5.txt", false))
    }
    */
    @Test
    fun testSupplyStacksPart1Solution2() {
        println(supplyStacksPart1Solution2("src/main/resources/input-day-5.txt", CrateMover.CrateMover9001))
    }

    // "unit" tests
    @Test
    fun testParseStacks() {
        val input: List<String> = listOf(
            "[H] [M] [N] [Z] [M] [C] [M] [P] [P]",
            "[P]     [L]         [T] [X]        ",
            "[A]                     [A]        "
        )
        val expectedOutput: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("H, P, A"),
            2 to mutableListOf("M"),
            3 to mutableListOf("N, L"),
            4 to mutableListOf("Z"),
            5 to mutableListOf("M"),
            6 to mutableListOf("C, T"),
            7 to mutableListOf("M, X, A"),
            8 to mutableListOf("P"),
            9 to mutableListOf("P")
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
            0 to mutableListOf(8, 3, 2),
            1 to mutableListOf(13, 8, 4),
            2 to mutableListOf(1, 4, 7)
        )
        assertEquals(expectedOutput, parseMoves(input))
    }

    @Test
    fun testDoMoveOperationsCrateMover9000() {
        val stacks: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("A", "B", "C", "D", "E", "F"),
            2 to mutableListOf("M"),
            3 to mutableListOf("X", "Y", "Z")
        )
        val inputInstructions: Map<Int, MutableList<Int>> = mutableMapOf(
            0 to mutableListOf(4, 1, 2),
            1 to mutableListOf(5, 2, 3),
            2 to mutableListOf(7, 3, 2)
        )
        val expectedOutput: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("A", "B"),
            2 to mutableListOf("M", "F", "E", "D", "C", "Z", "Y"),
            3 to mutableListOf("X")
        )
        assertEquals(expectedOutput, doMoveOperationsPart2(stacks, inputInstructions, CrateMover.CrateMover9000))
    }

    @Test
    fun testDoMoveOperationsCrateMover9001() {
        val stacks: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("Z", "N"),
            2 to mutableListOf("M", "C", "D"),
            3 to mutableListOf("P")
        )
        val inputInstructions: Map<Int, MutableList<Int>> = mutableMapOf(
            0 to mutableListOf(1, 2, 1),
            1 to mutableListOf(3, 1, 3),
            2 to mutableListOf(2, 2, 1),
            3 to mutableListOf(1, 1, 2)
        )
        val expectedOutput: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("M"),
            2 to mutableListOf("C"),
            3 to mutableListOf("P", "Z", "N", "D")
        )
        assertEquals(expectedOutput, doMoveOperationsPart2(stacks, inputInstructions, CrateMover.CrateMover9001))
    }

    @Test
    fun testResult() {
        val stacks: MutableMap<Int, MutableList<String>> = mutableMapOf(
            1 to mutableListOf("A", "B", "C", "D", "E", "F"),
            2 to mutableListOf("M"),
            3 to mutableListOf("X", "Y", "Z")
        )
        val expectedOutput = "FMZ"
        assertEquals(expectedOutput, calculateResult(stacks))
    }
}
