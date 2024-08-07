package programmers.BinarySearch

class ImmigrationInspection {
    fun solution(n: Int, times: IntArray): Long {
        times.sort()
        var answer = 0L
        var st = 1L
        var en = times[0] * n.toLong()
        while (st < en) {
            val mid = (st + en) / 2
            var cnt = 0L
            for (time in times) {
                cnt += mid / time
                if (cnt >= n) break
            }
            if (cnt >= n) {
                answer = mid
                en = mid
            } else {
                st = mid + 1
            }
        }
        return answer
    }
}

fun main() {
    val n = 6
    val times = intArrayOf(7, 10)
    val answer = ImmigrationInspection().solution(n, times)
    print(answer)
}