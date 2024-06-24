package boj.dp

class `2775` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val dp = Array(SIZE) { IntArray(SIZE) }
        for (i in dp.indices) dp[i][1] = 1
        for (j in 1 until SIZE) dp[0][j] = j
        for (i in 1 until SIZE) {
            for (j in 2 until SIZE)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        }
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val k = readLine().toInt()
            val n = readLine().toInt()
            sb.appendLine(dp[k][n])
        }
        print(sb)
    }

    companion object {
        private const val SIZE = 15
    }
}

fun main() {
    `2775`().solution()
}