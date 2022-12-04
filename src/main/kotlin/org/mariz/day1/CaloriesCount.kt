import java.io.File

fun readInputFile(fileName: String) = File(fileName)
    .forEachLine { println(it) }

fun readInputFile2(fileName: String) = File(fileName)
    .forEachLine { if (!it.equals("")) { println(it) } }

fun caloriesVar1(fileName: String) {
    var x: Int = 0
    var y: Int = 0
    File(fileName).forEachLine {
        if (it.isNotEmpty()) {
            x += it.toInt()
        } else {
            y = maxOf(y, x)
            x = 0
        }
    }
    println(y)
}

fun caloriesVar2(fileName: String) {
    val cals = mutableListOf<Number>()
    val sums = mutableListOf<Number>()
    File(fileName).forEachLine {
        if (it.isNotEmpty()) { cals.add(it.toInt())
        } else {
            sums.add(cals.sumOf { c -> c.toInt() })
            cals.clear()
        }
    }
    println(sums.maxOf { s -> s.toInt() })
}

fun caloriesTopThree(fileName: String) {
    val cals = mutableListOf<Int>()
    val sums = mutableListOf<Int>()
    File(fileName).forEachLine {
        if (it.isNotEmpty()) {
            cals.add(it.toInt())
        } else {
            sums.add(cals.sum())
            cals.clear()
        }
    }
    println(sums.sortedDescending().take(3).sum())
}
