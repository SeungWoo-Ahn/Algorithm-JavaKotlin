package programmers.Practice.Level3

class LongestPalindrome {
    fun solution(s: String): Int {
        val n = s.length
        val dp = Array(n) { BooleanArray(n) }
        for (i in dp.indices) {
            dp[i][i] = true
            if (i > 0 && s[i - 1] == s[i]) {
                dp[i - 1][i] = true
            }
        }
        var l: Int
        var r: Int
        for (gap in 2 until n) {
            for (st in 0 until n - gap) {
                l = st
                r = st + gap
                if (s[l] == s[r] && dp[l + 1][r - 1]) {
                    dp[l][r] = true
                }
            }
        }
        var answer = 1
        for (i in dp.indices) {
            for (j in i + 1 until n) {
                if (dp[i][j]) {
                    answer = maxOf(answer, j - i + 1)
                }
            }
        }
        return answer
    }
}

fun main() {
    val s = "abacde"
    val answer = LongestPalindrome().solution(s)
    print(answer)
}