import Symbol.*
import java.io.File

enum class Symbol(
    val value: Int
) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);
    fun value() = value
}

data class Hand(val symbol: Enum<Symbol>)

val elvesHand = mapOf(
    "X" to Hand(ROCK),
    "Y" to Hand(PAPER),
    "Z" to Hand(SCISSORS)
)

val opponentsHand = mapOf(
    "A" to Hand(ROCK),
    "B" to Hand(PAPER),
    "C" to Hand(SCISSORS)
)

fun evaluate(e: Hand, o: Hand): Int {
    return when {
        e.symbol == o.symbol -> 3
        e.symbol == ROCK && o.symbol == SCISSORS -> 6
        e.symbol == SCISSORS && o.symbol == PAPER -> 6
        e.symbol == PAPER && o.symbol == ROCK -> 6
        else -> 0
    }
}

fun play(fileName: String): Int {
    var score = 0
    File(fileName).forEachLine {
        val e = elvesHand[it.substringAfter(" ")]!!
        val o = opponentsHand[it.substringBefore(" ")]!!
        score += evaluate(e, o).plus(valueOf(e.symbol.name).value())
    }
    return score
}
