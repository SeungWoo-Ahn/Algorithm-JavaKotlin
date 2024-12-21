package boj.dp

import java.util.StringTokenizer

class `1965` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val sizes = IntArray(n) { st.nextToken().toInt() }
        val dp = IntArray(n)
        for (i in 0 until n) {
            dp[i] = 1
            for (j in 0 until i) {
                if (sizes[j] < sizes[i]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }
        print(dp.max())
    }
}

fun main() {
    `1965`().solution()
}