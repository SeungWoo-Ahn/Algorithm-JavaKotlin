package boj.dijkstra

import java.util.PriorityQueue

class `1238` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()
    private var adjReversed: Array<MutableList<Pair<Int, Int>>> = arrayOf()

    private fun Array<MutableList<Pair<Int, Int>>>.dijkstra(n: Int, st: Int): IntArray {
        val d = IntArray(n + 1) { INF }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        d[st] = 0
        pq.add(d[st] to st)
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (d[node] != cost) continue
            for ((nxtCost, nxtNode) in this[node]) {
                if (d[nxtNode] <= d[node] + nxtCost) continue
                d[nxtNode] = d[node] + nxtCost
                pq.add(d[nxtNode] to nxtNode)
            }
        }
        return d
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, x) = readLine().splitToInt()
        adj = Array(n + 1) { mutableListOf() }
        adjReversed = Array(n + 1) { mutableListOf() }
        repeat(m) {
            val (u, v, t) = readLine().splitToInt()
            adj[u] += t to v
            adjReversed[v] += t to u
        }
        val distanceToHome = adj.dijkstra(n, x)
        val distanceToParty = adjReversed.dijkstra(n, x)
        var maxDistance = 0
        for (i in 1 .. n) {
            val distance = distanceToHome[i] + distanceToParty[i]
            if (distance > maxDistance) maxDistance = distance
        }
        print(maxDistance)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 90_000_000
    }
}

fun main() {
    `1238`().solution()
}