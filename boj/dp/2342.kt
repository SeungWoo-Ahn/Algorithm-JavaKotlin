package boj.dp

import java.util.StringTokenizer

class `2342` {
    private val input = mutableListOf<Int>()
    private val dp = Array(100_001) { Array(5) { IntArray(5) { -1 } } }
    private val cost = listOf(
        intArrayOf(0, 2, 2, 2, 2),
        intArrayOf(0, 1, 3, 4, 3),
        intArrayOf(0, 3, 1, 3, 4),
        intArrayOf(0, 4, 3, 1, 3),
        intArrayOf(0, 3, 4, 3, 1),
    )

    private fun ddr(left: Int, right: Int, idx: Int): Int {
        if (idx == input.size) {
            return 0
        }
        if (dp[idx][left][right] != -1) {
            return dp[idx][left][right]
        }
        val nxt = input[idx]
        val nxtLeft = ddr(nxt, right, idx + 1) + cost[left][nxt]
        val nxtRight = ddr(left, nxt, idx + 1) + cost[right][nxt]
        dp[idx][left][right] = minOf(nxtLeft, nxtRight)
        return dp[idx][left][right]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine())
        while (true) {
            val num = st.nextToken().toInt()
            if (num == 0) break
            input += num
        }
        print(ddr(0, 0, 0))
    }
}

fun main() {
    `2342`().solution()
}