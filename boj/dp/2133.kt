package boj.dp

class `2133` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n % 2 == 1) {
            print(0)
            return
        }
        val k = n shr 1
        val dp = IntArray(k + 1)
        dp[0] = 1
        dp[1] = 3
        for (i in 2..k) {
            dp[i] = dp[i - 1] * 4 - dp[i - 2]
        }
        print(dp[k])
    }
}

fun main() {
    `2133`().solution()
}