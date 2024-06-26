package boj.priority_queue

import java.util.PriorityQueue

class `1927` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val pq = PriorityQueue<Int>()
        val sb = StringBuilder()
        val n = readLine().toInt()
        repeat(n) {
            when (val x = readLine().toInt()) {
                0 -> {
                    val min = if (pq.isNotEmpty()) pq.poll() else 0
                    sb.appendLine(min)
                }
                else -> pq += x
            }
        }
        print(sb)
    }
}

fun main() {
    `1927`().solution()
}