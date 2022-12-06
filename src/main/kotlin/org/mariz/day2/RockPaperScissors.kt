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

val winningRules = mapOf(
    ROCK to SCISSORS,
    SCISSORS to PAPER,
    PAPER to ROCK
)

fun evaluate(o: Hand, e: Hand): Int {
    return when (o.symbol) {
        e.symbol -> 3
        winningRules[e.symbol] -> 6
        else -> 0
    }
}

fun play(fileName: String): Int {
    var score = 0
    File(fileName).forEachLine {
        val o = opponentsHand[it.substringBefore(" ")]!!
        val e = elvesHand[it.substringAfter(" ")]!!
        score += evaluate(o, e).plus(valueOf(e.symbol.name).value())
    }
    return score
}
