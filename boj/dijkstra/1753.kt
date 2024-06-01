package boj.dijkstra

import java.util.PriorityQueue

class `1753` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (v, e) = readLine().splitToInt()
        val st = readLine().toInt()
        val d = IntArray(v + 1) { INF }
        val adj = Array(v + 1) { mutableListOf<Pair<Int, Int>>() }
        repeat(e) {
            val (i, j, w) = readLine().splitToInt()
            adj[i] += w to j
        }
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
        val sb = StringBuilder()
        for (i in 1 .. v) {
            if (d[i] == INF) sb.appendLine("INF")
            else sb.appendLine(d[i])
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 0x3f3f3f3f
    }
}

fun main() {
    `1753`().solution()
}