package boj.dp

import kotlin.math.min

class `1463` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val d = IntArray(n + 1).apply { this[0] = 1e6.toInt() }
        for (i in 2 .. n) {
            val d3 = if (i % 3 == 0) i / 3 else 0
            val d2 = if (i % 2 == 0) i / 2 else 0
            val m1 = i - 1
            d[i] = min(d[d3], min(d[d2], d[m1])) + 1
        }
        println(d[n])
    }
}

fun main() {
    `1463`().solution()
}