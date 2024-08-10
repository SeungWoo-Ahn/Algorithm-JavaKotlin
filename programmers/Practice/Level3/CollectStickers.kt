package programmers.Practice.Level3

class CollectStickers {
    fun solution(sticker: IntArray): Int {
        val n = sticker.size
        if (n < 3) {
            return sticker.max()
        }
        val dp1 = Array(n - 1) { IntArray(2) }
        dp1[1][1] = sticker[0]
        for (i in 2 until n - 1) {
            dp1[i][0] = dp1[i - 1][1] + sticker[i]
            dp1[i][1] = maxOf(dp1[i - 1][0], dp1[i - 1][1])
        }
        val dp2 = Array(n) { IntArray(2) }
        dp2[1][0] = sticker[1]
        for (i in 2 until n) {
            dp2[i][0] = dp2[i - 1][1] + sticker[i]
            dp2[i][1] = maxOf(dp2[i - 1][0], dp2[i - 1][1])
        }
        val dp1Max = maxOf(dp1[n - 2][0], dp1[n - 2][1])
        val dp2Max = maxOf(dp2[n - 1][0], dp2[n - 1][1])
        return maxOf(dp1Max, dp2Max)
    }
}

fun main() {
    val sticker = intArrayOf(14, 6, 5, 11, 3, 9, 2, 10)
    val answer = CollectStickers().solution(sticker)
    print(answer)
}