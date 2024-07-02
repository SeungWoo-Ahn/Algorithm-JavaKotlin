package boj.dp

class `12865` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().splitToInt()
        val things = Array(n) {
            val (w, v) = readLine().splitToInt()
            w to v
        }
        val dp = Array(n) { IntArray(k + 1) }
        val (fw, fv) = things[0]
        for (i in fw .. k) dp[0][i] = fv
        for (i in 1 until n) {
            val (w, v) = things[i]
            for (j in 1 .. k) {
                if (w > j) dp[i][j] = dp[i - 1][j]
                else dp[i][j] = maxOf(dp[i - 1][j], v + dp[i - 1][j - w])
            }
        }
        print(dp[n - 1][k])
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `12865`().solution()
}