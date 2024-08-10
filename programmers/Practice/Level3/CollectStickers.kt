package programmers.Practice.Level3

class CollectStickers {
    fun solution(sticker: IntArray): Int {
        val n = sticker.size
        if (n < 3) {
            return sticker.max()
        }
        val dp1 = IntArray(n)
        dp1[0] = sticker[0]
        dp1[1] = sticker[0]
        for (i in 2 until n - 1) {
            dp1[i] = maxOf(dp1[i - 1], dp1[i - 2] + sticker[i])
        }
        val dp2 = IntArray(n)
        dp2[0] = 0
        dp2[1] = sticker[1]
        for (i in 2 until n) {
            dp2[i] = maxOf(dp2[i - 1], dp2[i - 2] + sticker[i])
        }
        return maxOf(dp1[n - 2], dp2[n - 1])
    }
}

fun main() {
    val sticker = intArrayOf(14, 6, 5, 11, 3, 9, 2, 10)
    val answer = CollectStickers().solution(sticker)
    print(answer)
}