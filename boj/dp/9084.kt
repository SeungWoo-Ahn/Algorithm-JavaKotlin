package boj.dp

import java.util.StringTokenizer

class `9084` {
    private fun testCase(m: Int, coins: IntArray): Int {
        val dp = IntArray(m + 1)
        dp[0] = 1
        for (coin in coins) {
            for (cost in coin..m) {
                dp[cost] += dp[cost - coin]
            }
        }
        return dp[m]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        var st: StringTokenizer
        repeat(t) {
            val n = readLine().toInt()
            st = StringTokenizer(readLine())
            val coins = IntArray(n) { st.nextToken().toInt() }
            val m = readLine().toInt()
            sb.appendLine(testCase(m, coins))
        }
        print(sb)
    }
}

fun main() {
    `9084`().solution()
}