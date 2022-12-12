import java.io.File
import kotlin.math.min
import kotlin.math.roundToInt

enum class CrateMover(
    val chunkSize: Int
) {
    CrateMover9000(1),
    CrateMover9001(3);
}

fun supplyStacksPart1Solution2(fileName: String, CrateMover: CrateMover): String {
    val stackMap = parseStacks(splitFile(fileName)[0])
    val moveInstructions = parseMoves(splitFile(fileName)[1])
    doMoveOperationsPart2(stackMap, moveInstructions, CrateMover)
    return calculateResult(stackMap)
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

fun doMoveOperationsPart1(stacks: MutableMap<Int, MutableList<String>>, moveInstructions: Map<Int, MutableList<Int>>): MutableMap<Int, MutableList<String>> {
    for (i in 0 until moveInstructions.size) {
        val stackOut = moveInstructions[i]!!.elementAt(1)
        val stackIn = moveInstructions[i]!!.elementAt(2)
        for (instruction in 1..moveInstructions[i]!!.elementAt(0)) {
            val elementOut = stacks[stackOut]!!.last()
            stacks[stackIn]!!.add(elementOut)
            stacks[stackOut]!!.removeAt(stacks[stackOut]!!.lastIndex)
        }
    }
    return stacks
}

fun doMoveOperationsPart2(stacks: MutableMap<Int, MutableList<String>>, moveInstructions: Map<Int, MutableList<Int>>, CrateMover: CrateMover): MutableMap<Int, MutableList<String>> {
    for (i in 0 until moveInstructions.size) {
        var numberOfElementsOut = moveInstructions[i]!!.elementAt(0)
        val stackOut = moveInstructions[i]!!.elementAt(1)
        val stackIn = moveInstructions[i]!!.elementAt(2)
        val maxChunkSize = CrateMover.chunkSize
        val amountOfMoves = (moveInstructions[i]!!.elementAt(0) / maxChunkSize.toDouble() + 0.49999).roundToInt(); // round up to next Int
        println("---------------------------------------------------------")
        println("instruction: $i")
        println("---------------------------------------------------------")
        println("elements to be moved: $numberOfElementsOut")
        println("stackOut: $stackOut")
        println("stackIn : $stackIn")
        println("maxChunkSize: $maxChunkSize")
        println("amount of moves: $amountOfMoves")
        println("----------------------")
        println("initial situation")
        println("----------------------")
        println("stack out before move: ${stacks[stackOut]!!}")
        println("stack in  before move: ${stacks[stackIn]!!}")

        for (instruction in 1..amountOfMoves) {
            println("-----")
            println("number of elements to be removed with move $instruction: ${min(maxChunkSize, numberOfElementsOut)}")

            val elementsOut = stacks[stackOut]!!
                .reversed()
                .chunked(min(maxChunkSize, numberOfElementsOut))[0]
                .reversed();
            stacks[stackIn]!!.addAll(stacks[stackIn]!!.size, elementsOut)
            println("elements moved out in move $instruction: $elementsOut")
            println("stack in        after move $instruction: ${stacks[stackIn]!!}")
            for (i in 1..elementsOut.size) {
                stacks[stackOut]!!.removeAt(stacks[stackOut]!!.lastIndex)
            }
            numberOfElementsOut -= elementsOut.size
            println("number of remaining elements to be moved: $numberOfElementsOut")
        }
        println("----------------------")
        println("final situation")
        println("----------------------")
        println("stack out after move: ${stacks[stackOut]!!}")
        println("stack in  after move: ${stacks[stackIn]!!}")
    }
    return stacks
}

fun calculateResult(stacks: MutableMap<Int, MutableList<String>>): String {
    var result = ""
    for (i in 1..stacks.size) {
        result = result.plus(stacks[i]!!.last())
    }
    return result
}

/*
fun doMoveOperations9001(stacks: MutableMap<Int, MutableList<String>>, moveInstructions: Map<Int, MutableList<Int>>): MutableMap<Int, MutableList<String>> {
    for (i in 0 until moveInstructions.size) {
        var numberOfElementsOut = moveInstructions[i]!!.elementAt(0); println("numberOfElementsOut: $numberOfElementsOut")
        val stackOut = moveInstructions[i]!!.elementAt(1); println("stackOut: $stackOut")
        val stackIn = moveInstructions[i]!!.elementAt(2); println("stackIn: $stackIn")
        val maxChunkSize = Model.CRATE9001.chunkSize; println("maxChunkSize: $maxChunkSize")
        val amountOfMoves = (moveInstructions[i]!!.elementAt(0) / maxChunkSize.toDouble() + 0.49999).roundToInt(); println("amount of moves: $amountOfMoves") // round up to next Int

        println("stack out before move $i: ${stacks[stackOut]!!}")
        println("stack in before move $i: ${stacks[stackIn]!!}")

        for (instruction in 1..amountOfMoves) {
            val elementsOut = stacks[stackOut]!!
                .reversed()
                .chunked(min(maxChunkSize, numberOfElementsOut))[0]
                .reversed(); println("elementsOut in iteration $instruction: $elementsOut")
            stacks[stackIn]!!.addAll(stacks[stackIn]!!.size, elementsOut); println("stackIn in iteration $instruction: ${stacks[stackIn]!!}")
            println("remove, as min(maxChunkSize, numberOfElementsOut)): ${min(maxChunkSize, numberOfElementsOut)}")
            for (i in 1..elementsOut.size) {
                stacks[stackOut]!!.removeAt(stacks[stackOut]!!.lastIndex); println("stacks[stackOut]!! after removal $i: ${stacks[stackOut]!!}")
            }
            numberOfElementsOut -= elementsOut.size; println("numberOfRemainingElementsOut: $numberOfElementsOut")
        }
        println("stack out after move $i: ${stacks[stackOut]!!}")
        println("stack in after move $i: ${stacks[stackIn]!!}")
        println("----------------------------------------------")
    }
    return stacks
}
 */
