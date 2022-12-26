package org.mariz.day8

import java.io.File

typealias Matrix = Array<IntArray>
typealias TableList = MutableList<MutableList<Int>>

fun part1(fileName: String): Int {
    val inputMatrix: Matrix = readInput(fileName).toTable()
    return inputMatrix.countVisibleTrees()
}

fun part2(fileName: String): Int {
    val inputMatrix: Matrix = readInput(fileName).toTable()
    return inputMatrix.calculateScenicScore()
}

fun readInput(fileName: String): TableList {
    val tableList: TableList = mutableListOf()
    File(fileName).useLines {
        it.toList()
            .forEach { line ->
                val values = line
                    .split("")
                    .filterNot { it.isEmpty() }
                    .map { it.toInt() } as MutableList
                tableList.add(values)
            }
        return tableList
    }
}

fun TableList.toTable(): Matrix {
    val inputMatrix: Matrix = Array(this.size) { IntArray(this.size) }
    this.forEachIndexed { r, row ->
        row.forEachIndexed { c, item ->
            inputMatrix[r][c] = item
        }
    }
    return inputMatrix
}

fun Matrix.transpose(): Matrix {
    val transposed: Matrix = Array(this.size) { IntArray(this.size) }
    this.forEachIndexed { r, row ->
        row.forEachIndexed { c, item ->
            transposed[c][r] = item
        }
    }
    return transposed
}

fun Matrix.countVisibleTrees(): Int {
    var count = 0
    this.forEachIndexed() { r, row ->
        row.forEachIndexed() { c, _ ->
            if (row.isHidden(c).not() ||
                this.transpose()[c].isHidden(r).not()
            ) {
                count += 1
            }
        }
    }
    return count
}

fun Matrix.calculateScenicScore(): Int {
    var scenicScore = 0
    this.forEachIndexed() { r, row ->
        row.forEachIndexed() { c, _ ->
            scenicScore = maxOf(scenicScore, row.scenicScore(c) * this.transpose()[c].scenicScore(r))
        }
    }
    return scenicScore
}

fun IntArray.scenicScore(index: Int): Int {
    val scenicScore = if (this.isEdge(index)) {
        0
    } else {
        val itemsToTheRight = this
            .toList().subList(index + 1, this.size)
            .takeWhileInclusive { this.toList()[index] > it }
            .count()
        val itemsToTheLeft = this
            .toList().subList(0, index)
            .reversed()
            .takeWhileInclusive { this.toList()[index] > it }
            .count()
        itemsToTheRight * itemsToTheLeft
    }
    return scenicScore
}

fun IntArray.isEdge(index: Int): Boolean {
    return index == 0 || index == (this.size - 1)
}

fun IntArray.isHidden(index: Int): Boolean {
    return (
        this.isEdge(index).not() &&
            this.toList()[index] <= this.toList().subList(0, index).max() &&
            this.toList()[index] <= this.toList().subList(index + 1, this.toList().size).max()
        )
}

inline fun <T> Iterable<T>.takeWhileInclusive(
    predicate: (T) -> Boolean
): List<T> {
    var shouldContinue = true
    return takeWhile {
        val result = shouldContinue
        shouldContinue = predicate(it)
        result
    }
}
