import java.io.File

fun supplyStacksPart1(fileName: String, debug: Boolean): String {
    val temps = mapOf<Number, MutableList<String>> (
        1 to mutableListOf(),
        2 to mutableListOf(),
        3 to mutableListOf(),
        4 to mutableListOf(),
        5 to mutableListOf(),
        6 to mutableListOf(),
        7 to mutableListOf(),
        8 to mutableListOf(),
        9 to mutableListOf()
    )

    val stacks = mapOf<Number, MutableList<String>> (
        1 to mutableListOf(),
        2 to mutableListOf(),
        3 to mutableListOf(),
        4 to mutableListOf(),
        5 to mutableListOf(),
        6 to mutableListOf(),
        7 to mutableListOf(),
        8 to mutableListOf(),
        9 to mutableListOf()
    )

    var line = 1
    var result = ""

    File(fileName).forEachLine {
        // extract stack data into temps
        if (line <= 8) {
            for (i in 1..9) {
                if (it
                    .chunked(4)[i - 1]
                    .contains("   ", false)
                    .not()
                ) {
                    temps[i]!!.add(
                        it
                            .chunked(4)[i - 1]
                            .replace("[", "")
                            .replace("]", "")
                            .replace(" ", "")
                    )
                }
            }
        }
        // reverse temps into initial stacks situation
        if (line == 10) {
            for (i in 1..9) {
                stacks[i]!!.addAll(temps[i]!!.reversed())
            }
            if (debug) { println("stacks initial: $stacks") }
        }
        // stack transformation
        if (line >= 11) {
            var items = it
                .substringAfter(" ")
                .substringBefore(" ")
                .toInt()
            if (debug) { println("# items to be moved: $items") }

            var outof = it
                .substringAfter("from ")
                .substringBefore(" to")
                .toInt()
            if (debug) { println("stack out: $outof") }

            var into = it
                .substringAfterLast("")
                .toInt()
            if (debug) { println("stack in: $into") }

            for (i in 1..items) {
                stacks[into]!!.add(stacks[outof]!![stacks[outof]!!.lastIndex])
                stacks[outof]!!.removeAt(stacks[outof]!!.lastIndex)
            }
        }
        line += 1
    }

    // prepare result
    for (i in 1..9) {
        result = result.plus(stacks[i]!!.last())
    }

    return result
}
