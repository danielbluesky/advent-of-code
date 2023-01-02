package org.mariz.day10

import org.mariz.checkResult
import org.mariz.readInput

typealias Instruction = String
typealias Cycle = Int

fun main() {
    val testFile = "input-day-10-test"
    val file = "input-day-10"

    fun part1(input: List<String>): Int {
        var cycle = 0
        var signalStrength = 1
        val results = mutableListOf<Int>()
        input.forEach {
            for (i in 0..1) {
                cycle += 1
                if (cycle.isInteresting()) results.plusAssign(signalStrength * cycle)
                if (it.isNoOp()) break
            }
            signalStrength += it.getSignalStrength()
        }
        return results.sum()
    }

    fun part2(input: List<String>) = 0

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(testFile)
    checkResult(part1(testInput), 13140)
    // checkResult(part2(testInput), 0)

    // get result
    val input = readInput(file)
    checkResult(part1(input), 12520)
    // checkResult(part2(input), 0)
}

fun Instruction.isNoOp() = this == "noop"

fun Instruction.getSignalStrength() = if (this.isNoOp()) 0 else this.substringAfter(" ").toInt()

fun Cycle.isInteresting() = this == 20 || (this - 20) % 40 == 0