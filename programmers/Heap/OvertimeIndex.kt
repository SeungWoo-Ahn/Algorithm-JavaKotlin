package programmers.Heap

import java.util.Collections
import java.util.PriorityQueue

class OvertimeIndex {
    fun solution(n: Int, works: IntArray): Long {
        val pq = PriorityQueue<Int>(Collections.reverseOrder())
        for (work in works) {
            pq += work
        }
        repeat(n) {
            if (pq.isEmpty()) {
                return 0
            }
            val value = pq.poll()
            if (value - 1 > 0) {
                pq += value - 1
            }
        }
        var answer = 0L
        while (pq.isNotEmpty()) {
            val value = pq.poll()
            answer += value * value
        }
        return answer
    }
}

fun main() {
    val n = 4
    val works = intArrayOf(4, 3, 3)
    val answer = OvertimeIndex().solution(n, works)
    print(answer)
}