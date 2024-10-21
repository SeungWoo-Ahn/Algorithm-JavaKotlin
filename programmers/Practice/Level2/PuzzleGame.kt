package programmers.Practice.Level2

class PuzzleGame {
    private fun calcTotalTime(level: Int, diffs: IntArray, times: IntArray): Long {
        var totalTime = 0L
        for (i in diffs.indices) {
            totalTime += if (diffs[i] <= level) {
                times[i]
            } else {
                (diffs[i] - level) * (times[i] + times[i - 1]) + times[i]
            }
        }
        return totalTime
    }

    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        var st = 1
        var en = diffs.max()
        while (st < en) {
            val mid = (st + en) / 2
            val totalTime = calcTotalTime(mid, diffs, times)
            if (totalTime > limit) {
                st = mid + 1
            } else {
                en = mid
            }
        }
        return st
    }
}

fun main() {
    val diffs = intArrayOf(1, 99999, 100000, 99995)
    val times = intArrayOf(9999, 9001, 9999, 9001)
    val limit = 3_456_789_012L
    val answer = PuzzleGame().solution(diffs, times, limit)
    print(answer)
}