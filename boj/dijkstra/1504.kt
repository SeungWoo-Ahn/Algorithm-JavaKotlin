package boj.dijkstra

import java.util.PriorityQueue

class `1504` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()

    private fun dijkstra(n: Int, st: Int, en: Int): Int {
        val d = IntArray(n + 1) { INF }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        d[st] = 0
        pq.add(d[st] to st)
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (d[node] != cost) continue
            if (node == en) break
            for ((nxtCost, nxtNode) in adj[node]) {
                if (d[nxtNode] <= d[node] + nxtCost) continue
                d[nxtNode] = d[node] + nxtCost
                pq.add(d[nxtNode] to nxtNode)
            }
        }
        return d[en]
    }

    private fun distanceByCase(n: Int, v1: Int, v2: Int): Int {
        val stToV1 = dijkstra(n, 1, v1)
        val v1ToV2 = dijkstra(n, v1, v2)
        val v2ToEn = dijkstra(n, v2, n)
        return stToV1 + v1ToV2 + v2ToEn
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, e) = readLine().splitToInt()
        adj = Array(n + 1) { mutableListOf() }
        repeat(e) {
            val (a, b, c) = readLine().splitToInt()
            adj[a] += c to b
            adj[b] += c to a
        }
        val (v1, v2) = readLine().splitToInt()
        val case1 = distanceByCase(n, v1, v2)
        val case2 = distanceByCase(n, v2, v1)
        val answer = minOf(case1, case2)
        if (answer >= INF) println(-1)
        else println(answer)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 500_000_000
    }
}

fun main() {
    `1504`().solution()
}