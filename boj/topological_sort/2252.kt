package boj.topological_sort

import java.util.LinkedList
import java.util.Queue

class `2252` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        val adj = Array(n + 1) { mutableListOf<Int>() }
        val inDegree = IntArray(n + 1)
        repeat(m) {
            val (a, b) = readLine().splitToInt()
            adj[a].add(b)
            inDegree[b]++
        }
        val q = LinkedList<Int>() as Queue<Int>
        for (i in 1 .. n) {
            if (inDegree[i] == 0)
                q.offer(i)
        }
        val sb = StringBuilder()
        while (q.isNotEmpty()) {
            val cur = q.poll()
            sb.append("$cur ")
            for (nxt in adj[cur]) {
                inDegree[nxt]--
                if (inDegree[nxt] == 0)
                    q.offer(nxt)
            }
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `2252`().solution()
}