package org.mariz.day0

import org.mariz.checkResult
import org.mariz.readInput

fun main() {
    val testFile = "Day01_test"
    val file = "Day01"

    fun part1(input: List<String>) = 0
    fun part2(input: List<String>) = 0

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(testFile)
    checkResult(part1(testInput), 0)
    checkResult(part2(testInput), 0)

    // get result
    val input = readInput(file)
    checkResult(part1(input), 0)
    checkResult(part2(input), 0)
}
