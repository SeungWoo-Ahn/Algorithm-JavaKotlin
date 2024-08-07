package programmers.Graph

import java.util.LinkedList
import java.util.Queue

class FurthestNode {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var visited = booleanArrayOf()
    private var cost = intArrayOf()

    private fun bfs() {
        val q = LinkedList<Int>() as Queue<Int>
        q += 1
        visited[1] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (nxt in adj[cur]) {
                if (visited[nxt]) continue
                visited[nxt] = true
                cost[nxt] = cost[cur] + 1
                q += nxt
            }
        }
    }

    private fun findMaxCnt(n: Int): Int {
        var max = 0
        var cnt = 0
        for (i in 2..n) {
            if (cost[i] == max) {
                cnt++
            } else if (cost[i] > max) {
                max = cost[i]
                cnt = 1
            }
        }
        return cnt
    }

    fun solution(n: Int, edge: Array<IntArray>): Int {
        adj = Array(n + 1) { mutableListOf() }
        visited = BooleanArray(n + 1)
        cost = IntArray(n + 1)
        for (e in edge) {
            adj[e[0]] += e[1]
            adj[e[1]] += e[0]
        }
        bfs()
        return findMaxCnt(n)
    }
}

fun main() {
    val n = 6
    val edge = arrayOf(
        intArrayOf(3, 6),
        intArrayOf(4, 3),
        intArrayOf(3, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 2),
        intArrayOf(2, 4),
        intArrayOf(5, 2)
    )
    val answer = FurthestNode().solution(n, edge)
    print(answer)
}