package boj.dp

class `1309` {
    /**
     * i - 1번째 우리에서 사자 배치가
     * [0, 0] 일 경우 -> [0, 0], [1, 0], [0, 1] 가능
     * [1, 0] 일 경우 -> [0, 0], [0, 1] 가능
     * [0, 1] 일 경우 -> [0, 0], [1, 0] 가능
     * 그래서 [0, 0]의 경우의 수를 나타내는 dp[i][0]은 i - 1번째에서 세 가지 경우를 모두 더한 것
     *  [1, 0]과 [0, 1]의 경우의 수를 나타내는 dp[i][1]은 i - 1번째에서 [0, 0]과 나머지 하나의 경우를 더한 것
     */
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dp = Array(n) { IntArray(2) }
        dp[0][0] = 1
        dp[0][1] = 1
        for (i in 1 until n) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] * 2) % MOD
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % MOD
        }
        val answer = (dp[n - 1][0] + dp[n - 1][1] * 2) % MOD
        print(answer)
    }

    companion object {
        private const val MOD = 9_901
    }
}

fun main() {
    `1309`().solution()
}