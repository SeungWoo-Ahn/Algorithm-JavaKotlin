package boj.math

import kotlin.math.abs

class `20152` {
    private fun calc(diff: Int): Long {
        val dp = Array(diff + 1) { LongArray(diff + 1) }
        for (i in dp.indices) {
            dp[i][0] = 1
        }
        for (i in 1..diff) {
            for (j in 1..i) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        return dp[diff][diff]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (h, n) = readLine().split(" ").map { it.toInt() }
        val diff = abs(h - n)
        print(calc(diff))
    }
}

fun main() {
    `20152`().solution()
}