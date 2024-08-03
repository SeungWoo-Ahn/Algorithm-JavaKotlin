package programmers.DP

class Thievery {
    fun solution(money: IntArray): Int {
        val n = money.size
        val dp1 = IntArray(n - 1)
        dp1[0] = money[0]
        dp1[1] = money[0]
        for (i in 2 until n - 1) {
            dp1[i] = maxOf(dp1[i - 2] + money[i], dp1[i - 1])
        }
        val dp2 = IntArray(n)
        dp2[n - 1] = money[n - 1]
        dp2[n - 2] = money[n - 1]
        for (i in n - 3 downTo 1) {
            dp2[i] = maxOf(dp2[i + 2] + money[i], dp2[i + 1])
        }
        return maxOf(dp1[n - 2], dp2[1])
    }
}

fun main() {
    val money = intArrayOf(1, 2, 3, 1)
    val answer = Thievery().solution(money)
    print(answer)
}