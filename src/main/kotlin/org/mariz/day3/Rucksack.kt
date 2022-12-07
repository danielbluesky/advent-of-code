import java.io.File

fun rucksackPart1(fileName: String): Int {
    var result = 0
    File(fileName).forEachLine {
        val charSequences = it.chunked(it.length / 2)
        val sequence1table = charSequences[0].associate { char -> char to char.code }
        val sequence2string = charSequences[1]
        for (char in sequence1table.keys) {
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