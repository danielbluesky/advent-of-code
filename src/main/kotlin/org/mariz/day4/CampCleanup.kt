import java.io.File

fun campCleanupPart1(fileName: String): Int {
    var count = 0
    File(fileName).forEachLine {
        val s1low = it.substringBefore("-").toInt()
        val s1high = it.substringAfter("-").substringBefore(",").toInt()
        val s2low = it.substringAfter(",").substringBefore("-").toInt()
        val s2high = it.substringAfterLast("-").toInt()
        if ((s1low <= s2low && s1high >= s2high) || (s1low >= s2low && s1high <= s2high)) {
            count += 1
        }
    }
    return count
}

fun campCleanupPart2(fileName: String): Int {
    var count = 0
    File(fileName).forEachLine {
        val s1low = it.substringBefore("-").toInt()
        val s1high = it.substringAfter("-").substringBefore(",").toInt()
        val s2low = it.substringAfter(",").substringBefore("-").toInt()
        val s2high = it.substringAfterLast("-").toInt()
        if (s1high < s2low || s2high < s1low) { } else {
            count += 1
        }
    }
    return count
}