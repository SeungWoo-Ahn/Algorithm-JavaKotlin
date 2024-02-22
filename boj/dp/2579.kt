package boj.dp

import kotlin.math.min

class `2579` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val score = IntArray(n + 1)
        var total = 0
        repeat(n) { i ->
            readLine().toInt().let {
                score[i + 1] = it
                total += it
            }
        }
        if (n <= 2) {
            println(total)
            return
        }
        val d = IntArray(n + 1)
        d[1] = score[1]
        d[2] = score[2]
        d[3] = score[3]
        for (i in 4 until n) {
            d[i] = min(d[i - 2], d[i - 3]) + score[i]
        }
        println(total - min(d[n - 1], d[n - 2]))
    }
}

fun main() {
    `2579`().solution()
}