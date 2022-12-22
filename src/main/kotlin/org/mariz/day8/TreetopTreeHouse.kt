package org.mariz.day8

import java.io.File

typealias Matrix = Array<IntArray>
typealias TableList = MutableList<MutableList<Int>>

fun part1(fileName: String): Int {
    val inputMatrix: Matrix = readInput(fileName).toTable()
    return inputMatrix.countVisibleTrees()
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

fun Matrix.transpose(): Matrix {
    val transposed: Matrix = Array(this.size) { IntArray(this.size) }
    this.forEachIndexed { r, row ->
        row.forEachIndexed { c, item ->
            transposed[c][r] = item
        }
    }
    return transposed
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
