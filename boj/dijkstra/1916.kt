package boj.dijkstra

import java.util.PriorityQueue

class `1916` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        repeat(m) {
            val (u, v, cost) = readLine().splitToInt()
            adj[u] += cost to v
        }
        val (st, en) = readLine().splitToInt()
        val d = Array(n + 1) { INF }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        d[st] = 0
        pq.add(d[st] to st)
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (d[node] != cost) continue
            for ((nxtCost, nxtNode) in adj[node]) {
                if (d[nxtNode] <= d[node] + nxtCost) continue
                d[nxtNode] = d[node] + nxtCost
                pq.add(d[nxtNode] to nxtNode)
            }
        }
        println(d[en])
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 100_000_002
    }
}

fun main() {
    `1916`().solution()
}