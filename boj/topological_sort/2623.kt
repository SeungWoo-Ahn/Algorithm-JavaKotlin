package boj.topological_sort

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `2623` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val adj = Array(n + 1) { mutableListOf<Int>() }
        val inDegree = IntArray(n + 1)
        var st: StringTokenizer
        repeat(m) {
            st = StringTokenizer(readLine())
            val size = st.nextToken().toInt()
            val order = IntArray(size) { st.nextToken().toInt() }
            for (i in order.indices) {
                if (i != order.lastIndex) adj[order[i]].add(order[i + 1])
                if (i != 0) inDegree[order[i]]++
            }
        }
        val q = LinkedList<Int>() as Queue<Int>
        for (i in 1 .. n) {
            if (inDegree[i] == 0)
                q.offer(i)
        }
        val result = mutableListOf<Int>()
        while (q.isNotEmpty()) {
            val cur = q.poll()
            result.add(cur)
            for (nxt in adj[cur]) {
                inDegree[nxt]--
                if (inDegree[nxt] == 0)
                    q.offer(nxt)
            }
        }
        if (result.size != n) println(0)
        else println(result.joinToString("\n"))
    }
}

fun main() {
    `2623`().solution()
}