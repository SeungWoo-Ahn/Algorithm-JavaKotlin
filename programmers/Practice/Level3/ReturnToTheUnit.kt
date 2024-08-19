package programmers.Practice.Level3

import java.util.LinkedList
import java.util.Queue

class ReturnToTheUnit {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var costs = intArrayOf()

    private fun bfs(st: Int) {
        val q = LinkedList<Int>() as Queue<Int>
        q += st
        costs[st] = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (nxt in adj[cur]) {
                if (costs[nxt] >= 0) continue
                costs[nxt] = costs[cur] + 1
                q += nxt
            }
        }
    }

    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        adj = Array(n + 1) { mutableListOf() }
        costs = IntArray(n + 1) { -1 }
        for ((a, b) in roads) {
            adj[a] += b
            adj[b] += a
        }
        bfs(destination)
        val answer = IntArray(sources.size)
        for (i in sources.indices) {
            answer[i] = costs[sources[i]]
        }
        return answer
    }
}

fun main() {
    val n = 5
    val roads = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 4),
        intArrayOf(2, 4),
        intArrayOf(2, 5),
        intArrayOf(4, 5),
    )
    val sources = intArrayOf(1, 3, 5)
    val destination = 5
    val answer = ReturnToTheUnit().solution(n, roads, sources, destination)
    print(answer.joinToString(" "))
}