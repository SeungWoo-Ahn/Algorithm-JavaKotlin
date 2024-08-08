package programmers.BinarySearch

class SteppingStone {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        rocks.sort()
        val cnt = rocks.size
        val d = IntArray(cnt + 1)
        d[0] = rocks[0]
        d[cnt] = rocks[cnt - 1]
        for (i in 1 until cnt) {
            d[i] = rocks[i] - rocks[i - 1]
        }
        var st = 0
        var en = distance + 1
        while (st < en) {
            val mid = (st + en) / 2
            var removedRockCnt = 0
            var sum = 0
            for (di in d) {
                sum += di
                if (sum < mid) removedRockCnt++
                else sum = 0
            }
            if (removedRockCnt <= n) {
                st = mid + 1
            } else {
                en = mid
            }
        }
        return st - 1
    }
}

fun main() {
    val rocks = intArrayOf(2, 14, 11, 21, 17)
    val answer = SteppingStone().solution(25, rocks, 2)
    print(answer)
}