package boj.dp

class `24416` {
    private var recurCnt = 0
    private var dpCnt = 0

    private fun fibRecur(n: Int): Int {
        if (n == 1 || n == 2) {
            recurCnt++
            return 1
        }
        return fibRecur(n - 1) + fibRecur(n - 2)
    }

    private fun fibDp(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[1] = 1
        dp[2] = 1
        for (i in 3..n) {
            dpCnt++
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        fibRecur(n)
        fibDp(n)
        print("$recurCnt $dpCnt")
    }
}

fun main() {
    `24416`().solution()
}