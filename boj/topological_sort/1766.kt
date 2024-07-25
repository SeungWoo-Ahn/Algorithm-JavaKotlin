package boj.topological_sort

import java.util.PriorityQueue

class `1766` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        val inDegree = IntArray(n + 1)
        val adj = Array(n + 1) { mutableListOf<Int>() }
        repeat(m) {
            val (a, b) = readLine().splitToInt()
            adj[a] += b
            inDegree[b]++
        }
        val pq = PriorityQueue<Int>()
        for (i in 1..n) {
            if (inDegree[i] == 0) {
                pq += i
            }
        }
        val sb = StringBuilder()
        while (pq.isNotEmpty()) {
            val cur = pq.poll()
            sb.append("$cur ")
            for (nxt in adj[cur]) {
                inDegree[nxt]--
                if (inDegree[nxt] == 0) {
                    pq += nxt
                }
            }
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1766`().solution()
}