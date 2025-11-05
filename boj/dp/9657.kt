package boj.dp

class `9657` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dp = BooleanArray(1_001) // 선공 이기면 F, 후공 이기면 T
        val case = intArrayOf(1, 3, 4)
        dp[1] = false
        dp[2] = true
        dp[3] = false
        dp[4] = false
        for (i in 5..n) {
            for (j in case) {
                if (dp[i - j]) {
                    dp[i] = false
                    break
                } else {
                    dp[i] = true
                }
            }
        }
        if (dp[n]) {
            print("CY")
        } else {
            print("SK")
        }
    }
}

fun main() {
    `9657`().solution()
}