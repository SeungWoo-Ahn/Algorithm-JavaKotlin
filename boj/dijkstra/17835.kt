package boj.dijkstra

import java.util.PriorityQueue
import java.util.StringTokenizer

class `17835` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()
    private var d = longArrayOf()
    private val pq = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })

    private fun dijkstra() {
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (d[node] != cost) continue
            for ((nxtCost, nxtNode) in adj[node]) {
                val totalCost = nxtCost + cost
                if (d[nxtNode] <= totalCost) continue
                d[nxtNode] = totalCost
                pq.add(totalCost to nxtNode)
            }
        }
    }

    private fun findFurthestCity(n: Int): Int {
        var furthestCity = 0
        var maxDistance = 0L
        for (i in 1 .. n) {
            if (d[i] > maxDistance) {
                maxDistance = d[i]
                furthestCity = i
            }
        }
        return furthestCity
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readLine().splitToInt()
        adj = Array(n + 1) { mutableListOf() }
        d = LongArray(n + 1) { INF }
        repeat(m) {
            val (u, v, c) = readLine().splitToInt()
            adj[v] += c to u
        }
        val st = StringTokenizer(readLine())
        repeat(k) {
            val cityNum = st.nextToken().toInt()
            d[cityNum] = 0
            pq.add(d[cityNum] to cityNum)
        }
        dijkstra()
        val furthestCity = findFurthestCity(n)
        println("$furthestCity\n${d[furthestCity]}")
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = Long.MAX_VALUE
    }
}

fun main() {
    `17835`().solution()
}