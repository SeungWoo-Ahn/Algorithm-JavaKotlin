package boj.dijkstra

import java.util.PriorityQueue

class `1238` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()
    private var d = intArrayOf()

    private fun findToHomeDistance(x: Int) {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        d[x] = 0
        pq.add(d[x] to x)
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (d[node] != cost) continue
            for ((nxtCost, nxtNode) in adj[node]) {
                if (d[nxtNode] <= d[node] + nxtCost) continue
                d[nxtNode] = d[node] + nxtCost
                pq.add(d[nxtNode] to nxtNode)
            }
        }
    }

    private fun findToPartyDistance(n: Int, st: Int, en: Int): Int {
        val distance = IntArray(n + 1) { INF }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        distance[st] = 0
        pq.add(distance[st] to st)
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (distance[node] != cost) continue
            if (node == en) return cost
            for ((nxtCost, nxtNode) in adj[node]) {
                if (distance[nxtNode] <= distance[node] + nxtCost) continue
                distance[nxtNode] = distance[node] + nxtCost
                pq.add(distance[nxtNode] to nxtNode)
            }
        }
        return 0
    }

    private fun findMaxDistance(n: Int, x: Int): Int {
        var max = 0
        for (i in 1 .. n) {
            val distance = d[i] + findToPartyDistance(n, i, x)
            if (distance > max) max = distance
        }
        return max
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, x) = readLine().splitToInt()
        adj = Array(n + 1) { mutableListOf() }
        d = IntArray(n + 1) { INF }
        repeat(m) {
            val (u, v, t) = readLine().splitToInt()
            adj[u] += t to v
        }
        findToHomeDistance(x)
        print(findMaxDistance(n, x))
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 90_000_000
    }
}

fun main() {
    `1238`().solution()
}