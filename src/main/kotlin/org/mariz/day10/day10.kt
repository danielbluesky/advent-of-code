package org.mariz.day10

import org.mariz.checkResult
import org.mariz.readInput

typealias Instruction = String
typealias Cycle = Int
typealias Sprite = MutableList<Int>
typealias Screen = Sequence<Char>
typealias Cursor = Int

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

    fun part2(input: List<String>): String {
        var screen = emptySequence<Char>()
        val screenWidth = 40
        val sprite = mutableListOf<Int>().update(0)
        var spriteStart = 0
        var cursor = 0

        input.forEach { instruction ->
            for (i in 0..1) {
                screen = if (sprite.contains(cursor)) {
                    screen.plusElement('#'.toChar())
                } else {
                    screen.plusElement('.'.toChar())
                }
                cursor = cursor.update(screenWidth)
                if (instruction.isNoOp()) break
            }
            spriteStart += instruction.getValue()
            if (!instruction.isNoOp()) sprite.update(spriteStart)
        }
        return screen.prettyPrint(screenWidth)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(testFile)
    checkResult(part1(testInput), 13140)
    checkResult(
        part2(testInput),
        "" +
            "##..##..##..##..##..##..##..##..##..##..\n" +
            "###...###...###...###...###...###...###.\n" +
            "####....####....####....####....####....\n" +
            "#####.....#####.....#####.....#####.....\n" +
            "######......######......######......####\n" +
            "#######.......#######.......#######....."
    )

    // get result
    val input = readInput(file)
    checkResult(part1(input), 12520)
    checkResult(
        part2(input),
        "" +
            "####.#..#.###..####.###....##..##..#....\n" +
            "#....#..#.#..#....#.#..#....#.#..#.#....\n" +
            "###..####.#..#...#..#..#....#.#....#....\n" +
            "#....#..#.###...#...###.....#.#.##.#....\n" +
            "#....#..#.#....#....#....#..#.#..#.#....\n" +
            "####.#..#.#....####.#.....##...###.####."
    )
}

fun Instruction.isNoOp() = this == "noop"

fun Instruction.getSignalStrength() = if (this.isNoOp()) 0 else this.substringAfter(" ").toInt()

fun Cycle.isInteresting() = this == 20 || (this - 20) % 40 == 0

fun Instruction.getValue() = if (this.isNoOp()) 0 else this.substringAfter(" ").toInt()

fun Sprite.update(spriteStart: Int): Sprite {
    this.clear()
        .also { this.addAll(mutableListOf(spriteStart, spriteStart + 1, spriteStart + 2)) }
    return this
}

fun Cursor.update(lineBreakAfter: Int) = (this + 1) % lineBreakAfter
fun Screen.prettyPrint(lineBreakAfter: Int) = this
    .joinToString()
    .replace(",", "")
    .replace(" ", "")
    .chunked(lineBreakAfter)
    .joinToString("\n")


