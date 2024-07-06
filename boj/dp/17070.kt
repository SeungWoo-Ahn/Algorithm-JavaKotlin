package boj.dp

import java.util.StringTokenizer

class `17070` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        val map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        val dp = Array(n) { Array(n) { IntArray(3) } }
        for (i in 1 until n) {
            if (map[0][i] == 1) break
            dp[0][i][0] = 1
        }
        for (i in 1 until n) {
            for (j in 2 until n) {
                if (map[i][j] == 1) continue
                for (k in 0 until 3) {
                    when (k) {
                        0 -> {
                            if (map[i][j - 1] == 1) continue
                            dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2]
                        }
                        1 -> {
                            if (map[i - 1][j] == 1) continue
                            dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2]
                        }
                        2 -> {
                            if (map[i][j - 1] == 1 || map[i - 1][j] == 1 || map[i - 1][j - 1] == 1) continue
                            dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] +dp[i - 1][j - 1][2]
                        }
                    }
                }
            }
        }
        val answer = dp[n - 1][n - 1].sum()
        print(answer)
    }

}

fun main() {
    `17070`().solution()
}