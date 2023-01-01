package org.mariz.day9

import org.mariz.checkResult
import org.mariz.readInput
import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

fun main() {
    val testFile = "input-day-9-test"
    val file = "input-day-9"

    fun part1(input: List<String>, ropeLength: Int): Int {
        val visitedCoords = mutableSetOf<Coord>()
        val ropeCoords = initialiseRopeCoords(ropeLength)
        input.forEach {
            for (headMove in it.parseInstruction().translateInstruction()) {
                for (knot in ropeCoords.indices) {
                    if (knot.isHead()) ropeCoords.moveKnot(0, headMove) else {
                        val distance = ropeCoords.calculateDistance(knot)
                        if (distance.knotMustMove()) ropeCoords.moveKnot(knot, distance.calculateMove())
                    }
                }
                visitedCoords.add(ropeCoords[ropeCoords.size - 1])
            }
        }
        return visitedCoords.size
    }

    fun part2(input: List<String>, ropeLength: Int): Int {
        return part1(input, ropeLength)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(testFile)
    checkResult(part1(testInput, 2), 13)
    checkResult(part2(testInput, 10), 1)

    // get result
    val input = readInput(file)
    checkResult(part1(input, 2), 6057)
    checkResult(part2(input, 10), 2514)
}

data class Instruction(val direction: String, val distance: Int)
data class Delta(val x: Int, val y: Int)
data class Coord(val x: Int, val y: Int)
typealias CoordList = MutableList<Coord>
typealias Knot = Int

fun initialiseRopeCoords(ropeLength: Int) = MutableList(ropeLength) { Coord(0, 0) }

fun CoordList.calculateDistance(knot: Int) = Delta(this[knot - 1].x - this[knot].x, this[knot - 1].y - this[knot].y)

fun Delta.knotMustMove() = this.x.absoluteValue > 1 || this.y.absoluteValue > 1

fun Delta.calculateMove() = when {
    this.x.absoluteValue == 2 && this.y.absoluteValue == 2 -> Delta(this.x / 2, this.y / 2)
    this.x.absoluteValue == 2 -> Delta(this.x / 2, this.y)
    this.y.absoluteValue == 2 -> Delta(this.x, this.y / 2)
    else -> throw IllegalArgumentException()
}

fun Knot.isHead(): Boolean = this == 0

fun CoordList.moveKnot(knot: Int, delta: Delta): CoordList {
    this[knot] = Coord(this[knot].x + delta.x, this[knot].y + delta.y)
    return this
}

fun String.calculateHeadMove() = when (this) {
    "R" -> Delta(1, 0)
    "L" -> Delta(-1, 0)
    "U" -> Delta(0, 1)
    "D" -> Delta(0, -1)
    else -> throw IllegalArgumentException()
}

fun String.parseInstruction(): Instruction {
    return Instruction(
        this.substringBefore(" "),
        this.substringAfter(" ").toInt()
    )
}

fun Instruction.translateInstruction(): List<Delta> =
    List(this.distance) { this.direction.calculateHeadMove() }
