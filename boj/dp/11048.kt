package boj.dp

import java.util.StringTokenizer

class `11048` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        val dp = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(m) { st.nextToken().toInt() }
        }
        for (i in 1 until n) {
            dp[i][0] += dp[i - 1][0]
        }
        for (j in 1 until m) {
            dp[0][j] += dp[0][j - 1]
        }
        for (i in 1 until n) {
            for (j in 1 until m) {
                dp[i][j] += maxOf(dp[i - 1][j - 1], maxOf(dp[i - 1][j], dp[i][j - 1]))
            }
        }
        print(dp[n - 1][m - 1])
    }
}

fun main() {
    `11048`().solution()
}