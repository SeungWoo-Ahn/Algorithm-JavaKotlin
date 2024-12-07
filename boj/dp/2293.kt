package boj.dp

class `2293` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val coins = IntArray(n) { readLine().toInt() }
        val dp = IntArray(k + 1)
        dp[0] = 1
        for (i in 0 until n) {
            for (cost in 1..k) {
                if (cost - coins[i] < 0) continue
                dp[cost] += dp[cost - coins[i]]
            }
        }
        print(dp[k])
    }
}

fun main() {
    `2293`().solution()
}