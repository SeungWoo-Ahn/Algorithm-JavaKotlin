package boj.tree

import java.util.LinkedList
import java.util.Queue


class `1967` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()

    private fun bfs(n: Int, root: Int): Pair<Int, Int> {
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        val visited = BooleanArray(n + 1)
        q += root to 0
        visited[root] = true
        var furthestNode = root
        var furthestDis = 0
        while (q.isNotEmpty()) {
            val (node, dis) = q.poll()
            if (dis > furthestDis) {
                furthestNode = node
                furthestDis = dis
            }
            for ((nxt, weight) in adj[node]) {
                if (visited[nxt]) continue
                visited[nxt] = true
                q += nxt to dis + weight
            }
        }
        return furthestNode to furthestDis
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        adj = Array(n + 1) { mutableListOf() }
        repeat(n - 1) {
            val (u, v, weight) = readLine().split(" ").map { it.toInt() }
            adj[u] += v to weight
            adj[v] += u to weight
        }
        val s = bfs(n, 1).first
        val diameter = bfs(n, s).second
        print(diameter)
    }
}

fun main() {
    `1967`().solution()
}