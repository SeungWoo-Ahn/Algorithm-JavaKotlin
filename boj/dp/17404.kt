package boj.dp

import java.util.StringTokenizer

class `17404` {
    private var cost: Array<IntArray> = arrayOf()

    private fun calcMinCostByFirstHouse(n: Int, idx: Int): Int {
        val dp = Array(n) { IntArray(3) }
        val firstHouseCost = cost[0][idx]
        for (i in 0 until 3) {
            if (i == idx) dp[1][i] = 1_001 + firstHouseCost
            else dp[1][i] = cost[1][i] + firstHouseCost
        }
        for (i in 2 until n) {
            dp[i][0] = minOf(dp[i - 1][1], dp[i - 1][2]) + cost[i][0]
            dp[i][1] = minOf(dp[i - 1][2], dp[i - 1][0]) + cost[i][1]
            dp[i][2] = minOf(dp[i - 1][0], dp[i - 1][1]) + cost[i][2]
        }
        var minCost = 100_000_001
        for (i in 0 until 3) {
            if (i == idx) continue
            if (dp[n - 1][i] < minCost) {
                minCost = dp[n - 1][i]
            }
        }
        return minCost
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        cost = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(3) { st.nextToken().toInt() }
        }
        var minCost = 100_000_001
        for (i in 0 until 3) {
            val costByFirstHouse = calcMinCostByFirstHouse(n, i)
            if (costByFirstHouse < minCost) {
                minCost = costByFirstHouse
            }
        }
        print(minCost)
    }
}

fun main() {
    `17404`().solution()
}