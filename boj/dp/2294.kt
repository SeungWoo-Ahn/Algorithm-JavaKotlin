package boj.dp

class `2294` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val coins = IntArray(n) { readLine().toInt() }.apply { sort() }
        val dp = IntArray(k + 1)
        dp[0] = 0
        for (cost in 1..k) {
            var min = MAX
            for (i in coins.indices) {
                if (cost - coins[i] < 0) break
                min = minOf(min, dp[cost - coins[i]])
            }
            dp[cost] = min + 1
        }
        if (dp[k] > MAX) {
            print(-1)
        } else {
            print(dp[k])
        }
    }

    companion object {
        private const val MAX = 10_001
    }
}

fun main() {
    `2294`().solution()
}