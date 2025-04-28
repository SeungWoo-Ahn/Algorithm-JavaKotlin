package boj.dp

import java.util.StringTokenizer

class `21317` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        val costs = Array(n - 1) {
            st = StringTokenizer(readLine(), " ")
            val smallJumpCost = st.nextToken().toInt()
            val bigJumpCost = st.nextToken().toInt()
            intArrayOf(smallJumpCost, bigJumpCost)
        }
        val k = readLine().toInt()
        val dp = Array(n) { IntArray(2) { Int.MAX_VALUE } }
        dp[0][0] = 0
        dp[0][1] = 0
        for (i in costs.indices) {
            for (j in 0..1) {
                if (i + 1 < n) {
                    dp[i + 1][j] = minOf(dp[i + 1][j], dp[i][j] + costs[i][0])
                }
                if (i + 2 < n) {
                    dp[i + 2][j] = minOf(dp[i + 2][j], dp[i][j] + costs[i][1])
                }
                if (j == 1 && i + 3 < n) {
                    dp[i+ 3][0] = minOf(dp[i + 3][0], dp[i][1] + k)
                }
            }
        }
        val minCost = minOf(dp[n - 1][0], dp[n - 1][1])
        print(minCost)
    }
}

fun main() {
    `21317`().solution()
}