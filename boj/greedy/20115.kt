package boj.greedy

import java.util.Collections
import java.util.PriorityQueue
import java.util.StringTokenizer

class `20115` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine(), " ")
        val pq = PriorityQueue<Double>(Collections.reverseOrder())
        repeat(n) {
            pq += st.nextToken().toDouble()
        }
        while (pq.size > 1) {
            val max = pq.poll()
            val next = pq.poll()
            pq += max + (next / 2)
        }
        print(pq.peek())
    }
}

fun main() {
    `20115`().solution()
}