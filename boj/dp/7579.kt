package boj.dp

import java.util.StringTokenizer

class `7579` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st = StringTokenizer(readLine())
        val memories = IntArray(n) { st.nextToken().toInt() }
        var totalCost = 0
        st = StringTokenizer(readLine())
        val costs = IntArray(n) {
            val cost = st.nextToken().toInt()
            totalCost += cost
            cost
        }
        val dp = Array(n + 1) { IntArray(totalCost + 1) }
        var minCost = 10_000
        for (i in 1..n) {
            val memory = memories[i - 1]
            val cost = costs[i - 1]
            for (j in 0..totalCost) {
                if (cost > j) dp[i][j] = dp[i - 1][j]
                else dp[i][j] = maxOf(dp[i - 1][j], dp[i - 1][j - cost] + memory)
                if (dp[i][j] >= m) {
                    minCost = minOf(minCost, j)
                }
            }
        }
        print(minCost)
    }
}

fun main() {
    `7579`().solution()
}