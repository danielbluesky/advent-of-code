import java.io.File

fun supplyStacksPart1Solution2(fileName: String): String {
    val stackMap = parseStacks(splitFile("src/main/resources/input-day-5.txt")[0])
    val moveInstructions = parseMoves(splitFile("src/main/resources/input-day-5.txt")[1])
    doMoveOperations(stackMap, moveInstructions)
    return result(stackMap)
}

fun splitFile(fileName: String): List<List<String>> {
    val input = File(fileName).useLines { it.toList() }
    val inputForStacks = input.subList(0, 8).reversed()
    val inputForMoves = input.subList(10, input.size)
    return listOf(inputForStacks, inputForMoves)
}

fun parseStacks(input: List<String>): MutableMap<Int, MutableList<String>> {
    val map = mutableMapOf<Int, MutableList<String>>()
    for (item in 0..8) {
        map.plusAssign(item.plus(1) to mutableListOf<String>())
        for (line in input) {
            map[item.plus(1)]!!.plusAssign(
                line.chunked(4)[item]
                    .replace("   ", "[$]")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
            )
            map[item.plus(1)]!!.removeIf { it -> it == "$" }
        }
    }
    return map
}

fun parseMoves(input: List<String>): Map<Int, MutableList<Int>> {
    val map = mutableMapOf<Int, MutableList<Int>>()
    for (line in input.indices) {
        map.plusAssign(line to mutableListOf<Int>())
        val itemCount = input[line]
            .substringAfter(" ")
            .substringBefore(" ")
            .toInt()
        val outOf = input[line]
            .substringAfter("from ")
            .substringBefore(" to")
            .toInt()
        val into = input[line]
            .substringAfterLast("")
            .toInt()
        map[line]!!.plusAssign(listOf(itemCount, outOf, into))
    }
    return map
}

fun doMoveOperations(stacks: MutableMap<Int, MutableList<String>>, moveInstructions: Map<Int, MutableList<Int>>): MutableMap<Int, MutableList<String>> {
    // for (i in 0..moves.size) {
    for (i in 0 until moveInstructions.size) {
        val stackOut = moveInstructions[i]!!.elementAt(1)
        val stackIn = moveInstructions[i]!!.elementAt(2)
        for (itemCount in 1..moveInstructions[i]!!.elementAt(0)) {
            val elementOut = stacks[stackOut]!!.last()
            stacks[stackIn]!!.add(elementOut)
            stacks[stackOut]!!.removeAt(stacks[stackOut]!!.lastIndex)
        }
    }
    return stacks
}

fun result(stacks: MutableMap<Int, MutableList<String>>): String {
    var result = ""
    for (i in 1..9) {
        result = result.plus(stacks[i]!!.last())
    }
    return result
}