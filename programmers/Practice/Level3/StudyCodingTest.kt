package programmers.Practice.Level3

class StudyCodingTest {
    private fun getTarget(alp: Int, cop: Int, problems: Array<IntArray>): Pair<Int, Int> {
        var alpMax = 0
        var copMax = 0
        for (problem in problems) {
            alpMax = maxOf(alpMax, problem[0])
            copMax = maxOf(copMax, problem[1])
        }
        return maxOf(alp, alpMax) to maxOf(cop, copMax)
    }

    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val (alpTarget, copTarget) = getTarget(alp, cop, problems)
        val dp = Array(alpTarget + 1) { IntArray(copTarget + 1) { Int.MAX_VALUE } }
        val tests = mutableListOf<IntArray>()
        tests += intArrayOf(0, 0, 1, 0, 1)
        tests += intArrayOf(0, 0, 0, 1, 1)
        tests += problems
        dp[alp][cop] = 0
        for (alpVal in alp..alpTarget) {
            for (copVal in cop..copTarget) {
                for ((alpReq, copReq, alpRwd, copRwd, cost) in tests) {
                    if (alpVal < alpReq || copVal < copReq) continue
                    val alpPos = minOf(alpVal + alpRwd, alpTarget)
                    val copPos = minOf(copVal + copRwd, copTarget)
                    dp[alpPos][copPos] = minOf(dp[alpPos][copPos], dp[alpVal][copVal] + cost)
                }
            }
        }
        return dp[alpTarget][copTarget]
    }
}

fun main() {
    val alp = 10
    val cop = 10
    val problems = arrayOf(
        intArrayOf(10, 15, 2, 1, 2),
        intArrayOf(20, 20, 3, 3, 4),
    )
    val answer = StudyCodingTest().solution(alp, cop, problems)
    print(answer)
}
