package boj.dp

import java.util.StringTokenizer

class `11722` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        val dp = IntArray(n) { 1 }
        var max = 1
        for (i in arr.indices) {
            for (j in 0 until i) {
                if (arr[i] < arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1
                    max = maxOf(max, dp[i])
                }
            }
        }
        print(max)
    }
}

fun main() {
    `11722`().solution()
}