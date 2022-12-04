import java.io.File

fun caloriesVar1(fileName: String) {
    var calories: Int = 0
    var sums: Int = 0
    File(fileName).forEachLine {
        if (it.isNotEmpty()) {
            calories += it.toInt()
        } else {
            sums = maxOf(sums, calories)
            calories = 0
        }
    }
    println(sums)
}

fun caloriesVar2(fileName: String) {
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
    println(sums.max())
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
