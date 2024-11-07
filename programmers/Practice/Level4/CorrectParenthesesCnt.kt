package programmers.Practice.Level4

class CorrectParenthesesCnt {
    fun solution(n: Int): Int {
        val dp = IntArray(15)
        dp[0] = 1
        for (i in 1..n) {
            for (j in 0 until i) {
                dp[i] += dp[j] * dp[i - j - 1]
            }
        }
        return dp[n]
    }
}

fun main() {
    val n = 3
    val answer = CorrectParenthesesCnt().solution(n)
    print(answer)
}