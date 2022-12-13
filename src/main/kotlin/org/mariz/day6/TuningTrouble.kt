package org.mariz.day6

import java.io.File

fun tuningTrouble(fileName: String, seq: Int): Int {
    val input = File(fileName).readText().toMutableList()
    var marker = seq
    while (input.take(seq) != input.take(seq).distinct()) {
        marker += 1
        input.removeAt(0)
    }
    return marker
}
