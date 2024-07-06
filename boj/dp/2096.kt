package boj.dp

import java.util.StringTokenizer

class `2096` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        val map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(3) { st.nextToken().toInt() }
        }
        val dp = Array(n) { Array(3) { IntArray(2) } }
        for (i in 0 until 3) {
            for (j in 0..1) {
                dp[0][i][j] = map[0][i]
            }
        }
        for (i in 1 until n) {
            for (j in 0 until 3) {
                var max: Int
                var min: Int
                when (j) {
                    0 -> {
                        max = maxOf(dp[i - 1][0][0], dp[i - 1][1][0])
                        min = minOf(dp[i - 1][0][1], dp[i - 1][1][1])
                    }
                    1 -> {
                        max = maxOf(dp[i - 1][0][0], maxOf(dp[i - 1][1][0], dp[i - 1][2][0]))
                        min = minOf(dp[i - 1][0][1], minOf(dp[i - 1][1][1], dp[i - 1][2][1]))
                    }
                    else -> {
                        max = maxOf(dp[i - 1][1][0], dp[i - 1][2][0])
                        min = minOf(dp[i - 1][1][1], dp[i - 1][2][1])
                    }
                }
                dp[i][j][0] = map[i][j] + max
                dp[i][j][1] = map[i][j] + min
            }
        }
        val max = maxOf(dp[n - 1][0][0], maxOf(dp[n - 1][1][0], dp[n - 1][2][0]))
        val min = minOf(dp[n - 1][0][1], minOf(dp[n - 1][1][1], dp[n - 1][2][1]))
        print("$max $min")
    }
}

fun main() {
    `2096`().solution()
}