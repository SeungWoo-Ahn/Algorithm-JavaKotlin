package boj.dp

class `2482` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val k = readLine().toInt()
        val dp = Array(n + 1) { IntArray(k + 1) }
        for (i in 1..n) {
            dp[i][1] = i
        }
        for (i in 4..n) {
            for (j in 2..k) {
                if (j > i / 2) break
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD
            }
        }
        print(dp[n][k])
    }

    companion object {
        private const val MOD =  1_000_000_003
    }
}

fun main() {
    `2482`().solution()
}