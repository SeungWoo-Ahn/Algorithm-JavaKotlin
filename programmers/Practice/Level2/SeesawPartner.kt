package programmers.Practice.Level2

class SeesawPartner {
    private class Matcher(private val a: Int, private val b: Int) {
        fun proceed(num: Int): Int? {
            if (num % a != 0) {
                return null
            }
            return num / a * b
        }

        fun getPairCnt(cnt1: Long, cnt2: Long): Long {
            return if (a == b) cnt1 * (cnt1 - 1) / 2
            else cnt1 * cnt2
        }
    }

    fun solution(weights: IntArray): Long {
        val cnt = LongArray(1_001)
        for (weight in weights) {
            cnt[weight]++
        }
        val matchers = listOf(Matcher(1, 1), Matcher(2, 3), Matcher(3, 4), Matcher(1, 2))
        var result = 0L
        for (num in 1_00..1_000) {
            if (cnt[num] == 0L) continue
            for (matcher in matchers) {
                val matchingNum = matcher.proceed(num) ?: continue
                if (matchingNum > 1_000 || cnt[matchingNum] == 0L) continue
                result += matcher.getPairCnt(cnt[num], cnt[matchingNum])
            }
        }
        return result
    }
}

fun main() {
    val weights = intArrayOf(100,180,360,100,270)
    val answer = SeesawPartner().solution(weights)
    print(answer)
}