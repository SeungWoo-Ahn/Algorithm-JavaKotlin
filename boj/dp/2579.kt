package boj.dp

import kotlin.math.max

class `2579` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val score = IntArray(n + 1)
        repeat(n) { score[it + 1] = readLine().toInt() }
        if (n == 1) {
            println(score[1])
            return
        }
        val d = Array(n + 1) { IntArray(3)  }
        d[1][1] = score[1].also { d[1][2] = 0 }
        d[2][1] = score[2].also { d[2][2] = score[1] + score[2] }
        for (i in 2 .. n) {
            d[i][1] = max(d[i - 2][1], d[i - 2][2]) + score[i]
            d[i][2] = d[i - 1][1] + score[i]
        }
        println(max(d[n][1], d[n][2]))
    }
}

fun main() {
    `2579`().solution()
}