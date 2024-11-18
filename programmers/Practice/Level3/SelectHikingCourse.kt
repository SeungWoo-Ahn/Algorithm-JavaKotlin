package programmers.Practice.Level3

import java.util.PriorityQueue

class SelectHikingCourse {
    private data class Edge(val node: Int, val cost: Int) : Comparable<Edge> {
        override fun compareTo(other: Edge): Int {
            return cost - other.cost
        }
    }

    private fun getAdj(n: Int, paths: Array<IntArray>): Array<MutableList<Edge>> {
        val adj = Array(n + 1) { mutableListOf<Edge>() }
        for ((i, j, w) in paths) {
            adj[i] += Edge(j, w)
            adj[j] += Edge(i, w)
        }
        return adj
    }

    private fun getIsTarget(n: Int, targets: IntArray): BooleanArray {
        return BooleanArray(n + 1).apply {
            targets.forEach { this[it] = true }
        }
    }

    private fun getPathCost(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val adj = getAdj(n, paths)
        val isGate = getIsTarget(n, gates)
        val isSummit = getIsTarget(n, summits)
        val pathCost = IntArray(n + 1) { INF }
        val pq = PriorityQueue<Edge>()
        gates.forEach { gate ->
            pq += Edge(gate, 0)
            pathCost[gate] = 0
        }
        while (pq.isNotEmpty()) {
            val (node, cost) = pq.poll()
            if (isSummit[node]) continue
            if (pathCost[node] != cost) continue
            for ((nxtNode, nxtCost) in adj[node]) {
                if (isGate[nxtNode]) continue
                val maxCost = maxOf(pathCost[node], nxtCost)
                if (maxCost < pathCost[nxtNode]) {
                    pathCost[nxtNode] = maxCost
                    pq += Edge(nxtNode, pathCost[nxtNode])
                }
            }
        }
        return pathCost
    }

    private fun getResult(n: Int, pathCost: IntArray, summits: IntArray): IntArray {
        val result = intArrayOf(n, INF)
        for (summit in summits.sorted()) {
            if (pathCost[summit] < result[1]) {
                result[0] = summit
                result[1] = pathCost[summit]
            }
        }
        return result
    }

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val pathCost = getPathCost(n, paths, gates, summits)
        return getResult(n, pathCost, summits)
    }

    companion object {
        private const val INF = 10_000_000
    }
}

fun main() {
    val n = 6
    val paths = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(2, 3, 5),
        intArrayOf(2, 4, 2),
        intArrayOf(2, 5, 4),
        intArrayOf(3, 4, 4),
        intArrayOf(4, 5, 3),
        intArrayOf(4, 6, 1),
        intArrayOf(5, 6, 1)
    )
    val gates = intArrayOf(1, 3)
    val summits = intArrayOf(5)
    val answer = SelectHikingCourse().solution(n, paths, gates, summits)
    print(answer.joinToString())
}