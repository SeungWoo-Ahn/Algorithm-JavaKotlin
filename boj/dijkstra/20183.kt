package boj.dijkstra

import java.util.PriorityQueue
import java.util.StringTokenizer

class `20183` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()
    private val pq = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })

    private fun dijkstra(n: Int, st: Int, en: Int, reserves: Long, limit: Int): Boolean {
        val d = LongArray(n + 1) { INF }
        d[st] = 0
        pq.add(d[st] to st)
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (d[node] != cost) continue
            for ((nxtCost, nxtNode) in adj[node]) {
                if (nxtCost > limit) continue
                if (d[nxtNode] <= d[node] + nxtCost) continue
                d[nxtNode] = d[node] + nxtCost
                pq.add(d[nxtNode] to nxtNode)
            }
        }
        return d[en] <= reserves
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toLong()
        var lo = 1
        var hi = 1
        adj = Array(n + 1) { mutableListOf() }
        repeat(m) {
            val (u, v, cost) = readLine().split(" ").map { it.toInt() }
            adj[u] += cost to v
            adj[v] += cost to u
            hi = maxOf(hi, cost)
        }
        while (lo < hi) {
            val mid = (lo + hi) / 2
            if (dijkstra(n, a, b, c, mid)) hi = mid
            else lo = mid + 1
        }
        if (dijkstra(n, a, b, c, lo)) println(lo)
        else println(-1)
    }

    companion object {
        private const val INF = Long.MAX_VALUE
    }
}

fun main() {
    `20183`().solution()
}