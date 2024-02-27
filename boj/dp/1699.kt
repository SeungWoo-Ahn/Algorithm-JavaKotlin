package boj.dp

import kotlin.math.min

class `1699` {
    private val d = IntArray(100_001)

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        for (i in 1 .. n) {
            d[i] = i
            for (j in 1 .. i) {
                if (j * j > i) break
                d[i] = min(d[i], d[i - j * j] + 1)
            }
        }
        println(d[n])
    }
}

fun main() {
    `1699`().solution()
}