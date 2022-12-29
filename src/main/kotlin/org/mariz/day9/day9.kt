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
        var currentCoords = PairCoord(Coord(0,0), Coord(0,0))
        input.forEach {
            val instruction = parseInstruction(it)
            for (i in 1..instruction.distance) {
                currentCoords = currentCoords.moveHead(directionDelta(instruction.direction))
                if (currentCoords.calcDistance().tailMustMove()) {
                    currentCoords = currentCoords.moveTail(currentCoords.calcTailMove(currentCoords.calcDistance()))
                }
                visitedCoords.add(currentCoords.tail)
            }
        }
        return visitedCoords.size
    }

    fun part2(input: List<String>) = 0

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

// typealias Instruction = Pair<String, Int> // first = direction, second = distance

fun PairCoord.calcDistance() = Delta(this.head.x - this.tail.x, this.head.y - this.tail.y)

fun Delta.tailMustMove() = this.x.absoluteValue > 1 || this.y.absoluteValue > 1

fun PairCoord.calcTailMove(distance: Delta): Delta = when {
    distance.x.absoluteValue == 2 -> Delta(distance.x / 2, distance.y)
    distance.y.absoluteValue == 2 -> Delta(distance.x, distance.y / 2)
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

fun parseInstruction(instruction: String): Instruction {
    return Instruction(
        instruction.substringBefore(" "),
        instruction.substringAfter(" ").toInt()
    )
}

fun directionDelta(direction: String) = when (direction) {
    "R" -> Delta(1, 0)
    "L" -> Delta(-1, 0)
    "U" -> Delta(0, 1)
    "D" -> Delta(0, -1)
    else -> throw IllegalArgumentException()
}

fun Instruction.translateInstruction(): List<Delta> =
    List(this.distance) { directionDelta(this.direction) }