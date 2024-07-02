package boj.dp

class `9251` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val a = readLine()
        val b = readLine()
        val n = a.length + 1
        val m = b.length + 1
        val dp = Array(n) { IntArray(m) }
        for (i in 1 until n) {
            for (j in 1 until m) {
                dp[i][j] = if (a[i - 1] == b[j - 1]) dp[i - 1][j - 1] + 1
                          else maxOf(dp[i][j - 1], dp[i - 1][j])
            }
        }
        print(dp[n - 1][m - 1])
    }
}

fun main() {
    `9251`().solution()
}