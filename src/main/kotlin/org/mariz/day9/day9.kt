package org.mariz.day9

import org.mariz.checkResult
import org.mariz.readInput
import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

fun main() {
    val testFile = "input-day-9-test-1"
    val file = "input-day-9"

    fun part1(input: List<String>): Int {
        val visitedCoords = mutableSetOf<Coord>()
        var currentCoords = PairCoord(Coord(0, 0), Coord(0, 0))
        input.forEach {
            for (i in it.parseInstruction().translateInstruction()) {
                currentCoords = currentCoords.moveHead(i)
                val distance = currentCoords.calcDistance()
                if (distance.tailMustMove()) {
                    currentCoords = currentCoords.moveTail(distance.calcTailMove())
                }
                visitedCoords.add(currentCoords.tail)
            }
        }
        return visitedCoords.size
    }

    // fun part2(input: List<String>) = 0

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(testFile)
    checkResult(part1(testInput), 13)
    // checkResult(part2(testInput), 0)

    // get result
    val input = readInput(file)
    checkResult(part1(input), 6057)
    // checkResult(part2(input), 0)
}

data class Delta(val x: Int, val y: Int)
data class Coord(val x: Int, val y: Int)
data class PairCoord(val head: Coord, val tail: Coord)
data class Instruction(val direction: String, val distance: Int)

fun PairCoord.calcDistance() = Delta(this.head.x - this.tail.x, this.head.y - this.tail.y)

fun Delta.tailMustMove() = this.x.absoluteValue > 1 || this.y.absoluteValue > 1

fun Delta.calcTailMove(): Delta = when {
    this.x.absoluteValue == 2 -> Delta(this.x / 2, this.y)
    this.y.absoluteValue == 2 -> Delta(this.x, this.y / 2)
    else -> throw IllegalArgumentException()
}

fun PairCoord.moveTail(delta: Delta): PairCoord {
    val targetTail = Coord(this.tail.x + delta.x, this.tail.y + delta.y)
    return PairCoord(this.head, targetTail)
}

fun PairCoord.moveHead(delta: Delta): PairCoord {
    val targetHead = Coord(this.head.x + delta.x, this.head.y + delta.y)
    return PairCoord(targetHead, this.tail)
}

fun String.parseInstruction(): Instruction {
    return Instruction(
        this.substringBefore(" "),
        this.substringAfter(" ").toInt()
    )
}

fun String.directionDelta() = when (this) {
    "R" -> Delta(1, 0)
    "L" -> Delta(-1, 0)
    "U" -> Delta(0, 1)
    "D" -> Delta(0, -1)
    else -> throw IllegalArgumentException()
}

fun Instruction.translateInstruction(): List<Delta> =
    List(this.distance) { this.direction.directionDelta() }
