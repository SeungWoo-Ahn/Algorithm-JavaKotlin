package boj.tree

import java.util.StringTokenizer

class `20924` {
    private data class Edge(val next: Int, val cost: Int)
    private data class Route(val node: Int, val distance: Int)

    private var n = 0
    private var root = 0
    private lateinit var visited: BooleanArray
    private lateinit var adj: Array<MutableList<Edge>>

    private fun getGigaNodeRoute(node: Int, distance: Int): Route {
        visited[node] = true
        if ((node == root && adj[node].size != 1) || (node != root && adj[node].size != 2)) {
            return Route(node, distance)
        }
        var nextEdge = Edge(0, 0)
        for (edge in adj[node]) {
            if (visited[edge.next]) continue
            nextEdge = edge
        }
        return getGigaNodeRoute(nextEdge.next, nextEdge.cost + distance)
    }

    private fun getMaxBranchDistance(node: Int, distance: Int): Int {
        visited[node] = true
        if (adj[node].size == 1) {
            return distance
        }
        var maxBranchDistance = 0
        for (edge in adj[node]) {
            if (visited[edge.next]) continue
            val branchDistance = getMaxBranchDistance(edge.next, edge.cost + distance)
            if (branchDistance > maxBranchDistance) {
                maxBranchDistance = branchDistance
            }
        }
        return maxBranchDistance
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        adj = Array(n + 1) { mutableListOf() }
        visited = BooleanArray(n + 1)
        root = st.nextToken().toInt()
        repeat(n - 1) {
            st = StringTokenizer(readLine(), " ")
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            val d = st.nextToken().toInt()
            adj[a] += Edge(b, d)
            adj[b] += Edge(a, d)
        }
    }

    fun solution() {
        input()
        if (n == 1) {
            print("0 0")
            return
        }
        val (gigaNode, gigaNodeDistance) = getGigaNodeRoute(root, 0)
        val maxBranchDistance = getMaxBranchDistance(gigaNode, 0)
        print("$gigaNodeDistance $maxBranchDistance")
    }
}

fun main() {
    `20924`().solution()
}