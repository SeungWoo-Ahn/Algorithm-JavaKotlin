package boj.dp

class `17626` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dp = IntArray(n + 1)
        dp[1] = 1
        var min: Int
        for (i in 2 .. n) {
            min = 4
            for (j in 1 .. i) {
                if (j * j > i) break
                val idx = i - j * j
                min = minOf(min, dp[idx])
            }
            dp[i] = min + 1
        }
        print(dp[n])
    }
}

fun main() {
    `17626`().solution()
}