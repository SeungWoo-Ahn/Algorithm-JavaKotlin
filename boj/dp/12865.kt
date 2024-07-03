package boj.dp

class `12865` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().splitToInt()
        val things = Array(n) {
            val (w, v) = readLine().splitToInt()
            w to v
        }
        val dp = Array(n + 1) { IntArray(k + 1) }
        for (i in 1 .. n) {
            val (w, v) = things[i - 1]
            for (j in 1 .. k) {
                if (w > j) dp[i][j] = dp[i - 1][j]
                else dp[i][j] = maxOf(dp[i - 1][j], v + dp[i - 1][j - w])
            }
        }
        print(dp[n][k])
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `12865`().solution()
}