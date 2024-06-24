package boj.priority_queue

import java.util.PriorityQueue

class `11651` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.second }, { it.first }))
        repeat(n) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            pq += x to y
        }
        val sb = StringBuilder()
        while (pq.isNotEmpty()) {
            val (x, y) = pq.poll()
            sb.appendLine("$x $y")
        }
        print(sb)
    }
}

fun main() {
    `11651`().solution()
}