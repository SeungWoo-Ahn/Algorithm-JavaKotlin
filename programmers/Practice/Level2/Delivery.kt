package programmers.Practice.Level2

import java.util.PriorityQueue

class Delivery {
    private data class Edge(val node: Int, val cost: Int) : Comparable<Edge> {
        override fun compareTo(other: Edge): Int {
            return cost - other.cost
        }
    }

    private fun makeAdj(n: Int, road: Array<IntArray>): Array<MutableList<Edge>> {
        val adj = Array(n + 1) { mutableListOf<Edge>() }
        for ((a, b, c) in road) {
            adj[a] += Edge(b, c)
            adj[b] += Edge(a, c)
        }
        return adj
    }

    private fun dijkstra(n: Int, adj: Array<MutableList<Edge>>): IntArray {
        val d = IntArray(n + 1) { INF }
        val pq = PriorityQueue<Edge>()
        d[1] = 0
        pq += Edge(1, 0)
        while (pq.isNotEmpty()) {
            val (node, cost) = pq.poll()
            if (d[node] != cost) continue
            for ((nxtNode, nxtCost) in adj[node]) {
                if (d[nxtNode] <= d[node] + nxtCost) continue
                d[nxtNode] = d[node] + nxtCost
                pq += Edge(nxtNode, d[nxtNode])
            }
        }
        return d
    }

    fun solution(n: Int, road: Array<IntArray>, k: Int): Int {
        val adj = makeAdj(n, road)
        return dijkstra(n, adj).count { it <= k }
    }

    companion object {
        private const val INF = 20_000_001
    }
}

fun main() {
    val n = 5
    val road = arrayOf(
        intArrayOf(1, 2, 1),
        intArrayOf(2, 3, 3),
        intArrayOf(5, 2, 2),
        intArrayOf(1, 4, 2),
        intArrayOf(5, 3, 1),
        intArrayOf(5, 4, 2)
    )
    val k = 3
    val answer = Delivery().solution(n, road, k)
    print(answer)
}