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

val loosingRules = winningRules
    .toList()
    .associate { (k, v) -> v to k }

// find symbol to match expected outcome (part 2)
fun getElvesHand(o: Hand, outcome: String): Hand {
    return when (outcome) {
        "Y" -> elvesHand[elvesHand.filterValues { it == Hand(o.symbol) }.keys.first()]!!
        "X" -> elvesHand[elvesHand.filterValues { it == Hand(winningRules[o.symbol]!!) }.keys.first()]!!
        "Z" -> elvesHand[elvesHand.filterValues { it == Hand(loosingRules[o.symbol]!!) }.keys.first()]!!
        else -> throw Exception("Not found")
    }
}

fun evaluate(o: Hand, e: Hand): Int {
    return when (o.symbol) {
        e.symbol -> 3
        winningRules[e.symbol] -> 6
        else -> 0
    }
}

fun playPart1(fileName: String): Int {
    var score = 0
    File(fileName).forEachLine {
        val o = opponentsHand[it.substringBefore(" ")]!!
        val e = elvesHand[it.substringAfter(" ")]!!
        score += evaluate(o, e).plus(valueOf(e.symbol.name).value())
    }
    return score
}

fun playPart2(fileName: String): Int {
    var score = 0
    File(fileName).forEachLine {
        val o = opponentsHand[it.substringBefore(" ")]!!
        val e = getElvesHand(o, it.substringAfter(" "))
        score += evaluate(o, e).plus(valueOf(e.symbol.name).value())
    }
    return score
}
