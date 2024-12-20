package boj.dp

class `14916` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dp = IntArray(100_001)
        dp[1] = -1
        dp[2] = 1
        dp[3] = -1
        dp[4] = 2
        dp[5] = 1
        for (cost in 6..n) {
            val bt = if (dp[cost - 2] < 0) Int.MAX_VALUE else dp[cost - 2]
            val bf = if (dp[cost - 5] < 0) Int.MAX_VALUE else dp[cost - 5]
            dp[cost] = minOf(bt, bf) + 1
        }
        print(dp[n])
    }
}

fun main() {
    `14916`().solution()
}