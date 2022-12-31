/*
package org.mariz.day9b

import org.mariz.checkResult
import org.mariz.readInput
import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

// obsolete solution using maps
fun main() {
    val testFile = "input-day-9-test"
    val file = "input-day-9"

    fun part1(input: List<String>): Int {
        val visitedCoords = mutableSetOf<Coord>()
        val coordMap = mutableMapOf(
            0 to Coord(0, 0),
            1 to Coord(0, 0)
        )
        input.forEach {
            for (i in it.parseInstruction().translateInstruction()) {
                coordMap.moveHead(i)
                for (i in coordMap) {
                    if (i.key > 0) {
                        val distance = coordMap.calcDistance(i.key)
                        if (distance.followerMustMove()) {
                            coordMap.moveFollower(i.key, distance.calcFollowerMove())
                        }
                    }
                }
                visitedCoords.add(coordMap[1]!!)
            }
        }
        return visitedCoords.size
    }

    fun part2(input: List<String>): Int {
        val visitedCoords = mutableSetOf<Coord>()
        val coordMap = mutableMapOf(
            0 to Coord(0, 0),
            1 to Coord(0, 0),
            2 to Coord(0, 0),
            3 to Coord(0, 0),
            4 to Coord(0, 0),
            5 to Coord(0, 0),
            6 to Coord(0, 0),
            7 to Coord(0, 0),
            8 to Coord(0, 0),
            9 to Coord(0, 0)
        )
        input.forEach {
            for (i in it.parseInstruction().translateInstruction()) {
                coordMap.moveHead(i)
                for (i in coordMap) {
                    if (i.key > 0) {
                        val distance = coordMap.calcDistance(i.key)
                        if (distance.followerMustMove()) {
                            coordMap.moveFollower(i.key, distance.calcFollowerMove())
                        }
                    }
                }
                visitedCoords.add(coordMap[9]!!)
            }
        }
        return visitedCoords.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(testFile)
    checkResult(part1(testInput), 13)
    checkResult(part2(testInput), 1)

    // get result
    val input = readInput(file)
    checkResult(part1(input), 6057)
    checkResult(part2(input), 2514)
}

data class Delta(val x: Int, val y: Int)
data class Coord(val x: Int, val y: Int)
data class Instruction(val direction: String, val distance: Int)
typealias CoordMap = MutableMap<Int, Coord>

fun CoordMap.calcDistance(follower: Int) = Delta(this[follower - 1]!!.x - this[follower]!!.x, this[follower - 1]!!.y - this[follower]!!.y)

fun Delta.followerMustMove() = this.x.absoluteValue > 1 || this.y.absoluteValue > 1

fun Delta.calcFollowerMove(): Delta = when {
    this.x.absoluteValue == 2 && this.y.absoluteValue == 2 -> Delta(this.x / 2, this.y / 2)
    this.x.absoluteValue == 2 -> Delta(this.x / 2, this.y)
    this.y.absoluteValue == 2 -> Delta(this.x, this.y / 2)
    else -> throw IllegalArgumentException()
}

fun CoordMap.moveFollower(follower: Int, delta: Delta): CoordMap {
    this[follower] = Coord(this[follower]!!.x + delta.x, this[follower]!!.y + delta.y)
    return this
}

fun CoordMap.moveHead(delta: Delta): CoordMap {
    this[0] = Coord(this[0]!!.x + delta.x, this[0]!!.y + delta.y)
    return this
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

*/
