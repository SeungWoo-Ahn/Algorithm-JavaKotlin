package programmers.Practice.Level3

class CountDown {
    private var dp: Array<IntArray> = arrayOf()

    private fun fill(num: Int, s: Int, isSingleOrBull: Boolean) {
        val offset = if (isSingleOrBull) 1 else 0
        if (dp[num][0] + 1 < dp[num + s][0]) {
            dp[num + s][0] = dp[num][0] + 1
            dp[num + s][1] = dp[num][1] + offset
        } else if (dp[num][0] + 1 == dp[num + s][0] && dp[num][1] + offset > dp[num + s][1]) {
            dp[num + s][1] = dp[num][1] + offset
        }
    }

    fun solution(target: Int): IntArray {
        dp = Array(target + 1) { intArrayOf(100_000, -1) }
        dp[0][0] = 0
        dp[0][1] = 0
        for (num in 0 until target) {
            for (s in 1..20) {
                if (num + s > target) break
                fill(num, s, true)
            }
            for (s in 2..40 step 2) {
                if (num + s > target) break
                fill(num, s, false)
            }
            for (s in 3..60 step 3) {
                if (num + s > target) break
                fill(num, s, false)
            }
            if (num + 50 > target) continue
            fill(num, 50, true)
        }
        return dp[target]
    }
}

fun main() {
    val target = 58
    val answer = CountDown().solution(target)
    print(answer.joinToString())
}