package org.mariz.day8

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.jupiter.api.Test

val file = "src/main/resources/input-day-8.txt"
val testFile = "src/main/resources/input-day-8-test-1.txt"

val testMatrix: Matrix = arrayOf(
    intArrayOf(3, 0, 3, 7, 3),
    intArrayOf(2, 5, 5, 1, 2),
    intArrayOf(6, 5, 3, 3, 2),
    intArrayOf(3, 3, 5, 4, 9),
    intArrayOf(3, 5, 3, 9, 0)
)

val testMatrix2: Matrix = arrayOf(
    intArrayOf(9, 9, 9, 9, 9),
    intArrayOf(9, 8, 7, 6, 9),
    intArrayOf(9, 5, 4, 3, 9),
    intArrayOf(9, 2, 1, 0, 9),
    intArrayOf(9, 9, 9, 9, 9)
)

val testMatrix3: Matrix = arrayOf(
    intArrayOf(1, 1, 1, 1, 1),
    intArrayOf(1, 2, 2, 2, 1),
    intArrayOf(1, 2, 2, 2, 1),
    intArrayOf(1, 2, 2, 2, 1),
    intArrayOf(1, 1, 1, 1, 1)
)

val testMatrixTransposed: Matrix = arrayOf(
    intArrayOf(3, 2, 6, 3, 3),
    intArrayOf(0, 5, 5, 3, 5),
    intArrayOf(3, 5, 3, 5, 3),
    intArrayOf(7, 1, 3, 4, 9),
    intArrayOf(3, 2, 2, 9, 0)
)

val testArray = intArrayOf(3, 2, 6, 3, 2)

internal class TreetopTreeHouseKtTest {

    // results
    @Test
    fun testPart1() {
        println(part1(testFile))
        assertEquals(21, part1(testFile))
    }

    @Test
    fun testPart2() {
        println(part2(testFile))
        assertEquals(8, part2(testFile))
    }

    @Test
    fun part1() {
        println(part1(file))
        assertEquals(1845, part1(file))
    }

    @Test
    fun part2() {
        println(part2(file))
        assertEquals(230112, part2(file))
    }

    // tests
    @Test
    fun treeTopTreeHouseTest1() {
        assertEquals(21, testMatrix.countVisibleTrees())
    }

    @Test
    fun treeTopTreeHouseTest2() {
        assertEquals(16, testMatrix2.countVisibleTrees())
    }

    @Test
    fun treeTopTreeHouseTest3() {
        assertEquals(24, testMatrix3.countVisibleTrees())
    }

    @Test
    fun isHiddenTest() {
        assertFalse(testArray.isHidden(0))
        assertTrue(testArray.isHidden(1))
        assertFalse(testArray.isHidden(2))
        assertFalse(testArray.isHidden(3))
        assertFalse(testArray.isHidden(4))
    }

    /*
    @Test
    fun isVisibleTest() {
        assertTrue(testArray.isVisible(0))
        assertFalse(testArray.isVisible(1))
        assertTrue(testArray.isVisible(2))
        assertTrue(testArray.isVisible(3))
        assertTrue(testArray.isVisible(4))
    }
    */

    @Test
    fun isEdgeTest() {
        assertTrue(testArray.isEdge(0))
        assertFalse(testArray.isEdge(1))
        assertFalse(testArray.isEdge(2))
        assertFalse(testArray.isEdge(3))
        assertTrue(testArray.isEdge(4))
    }

    @Test
    fun transposeMatrixTest() {
        assertEquals(testMatrix.transpose()[0].toList(), testMatrixTransposed[0].toList())
        assertEquals(testMatrix.transpose()[1].toList(), testMatrixTransposed[1].toList())
        assertEquals(testMatrix.transpose()[2].toList(), testMatrixTransposed[2].toList())
        assertEquals(testMatrix.transpose()[3].toList(), testMatrixTransposed[3].toList())
        assertEquals(testMatrix.transpose()[4].toList(), testMatrixTransposed[4].toList())
    }
}
