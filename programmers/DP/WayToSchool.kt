package programmers.DP

class WayToSchool {
    fun solution(m: Int, n: Int, puddles: Array<IntArray>): Int {
        val dp = Array(n + 1) { IntArray(m + 1) }
        val isPuddle = Array(n + 1) { BooleanArray(m + 1) }
        for (p in puddles) {
            isPuddle[p[1]][p[0]] = true
        }
        dp[1][1] = 1
        for (i in 1..n) {
            for (j in 1..m) {
                if (i == 1 && j == 1) continue
                if (isPuddle[i][j]) continue
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD
            }
        }
        return dp[n][m]
    }

    companion object {
        private const val MOD = 1_000_000_007
    }
}

fun main() {
    val m = 4
    val n = 3
    val puddles = arrayOf(
        intArrayOf(2, 2)
    )
    val answer = WayToSchool().solution(m, n, puddles)
    print(answer)
}