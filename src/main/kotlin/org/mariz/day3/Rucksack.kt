import java.io.File

fun rucksackPart1(fileName: String): Int {
    var result = 0
    File(fileName).forEachLine {
        val charSequences = it.chunked(it.length / 2)
        val sequence1map = charSequences[0].associate { char -> char to char.code }
        val sequence2string = charSequences[1]
        for (char in sequence1map.keys) {
            if ((sequence2string.contains(char, false))) {
                result += when {
                    char.isLowerCase() -> char.code.minus(96) // ascii offset for lowerCase
                    char.isUpperCase() -> char.code.minus(38) // ascii offset for upperCase
                    else -> 0
                }
            }
        }
    }
    return result
}

fun rucksackPart2(fileName: String): Int {
    var result = 0
    var loop = 0
    val elfe1List = mutableSetOf<Char>()
    val elfe2List = mutableSetOf<Char>()
    val elfe3List = mutableSetOf<Char>()
    val itemList = mutableListOf<Char>()

    File(fileName).forEachLine {
        loop += 1

        if (loop == 1) { elfe1List.plusAssign(it.associate { char -> char to null }.keys) }
        if (loop == 2) { elfe2List.plusAssign(it.associate { char -> char to null }.keys) }
        if (loop == 3) {
            elfe3List.plusAssign(it.associate { char -> char to null }.keys)
            itemList.plusAssign((elfe1List intersect elfe2List intersect elfe3List).toList())

            result += when {
                itemList[0].isLowerCase() -> itemList[0].code.minus(96) // ascii offset for lowerCase
                itemList[0].isUpperCase() -> itemList[0].code.minus(38) // ascii offset for upperCase
                else -> 0
            }

            elfe1List.clear()
            elfe2List.clear()
            elfe3List.clear()
            itemList.clear()
            loop = 0
        }
    }
    return result
}
