package boj.tree

import java.util.StringTokenizer

class `17073` {
    private var w = 0
    private var leaf = 0.0
    private lateinit var visited: BooleanArray
    private lateinit var adj: Array<MutableList<Int>>

    private fun dfs(node: Int) {
        visited[node] = true
        var isLeaf = true
        for (nxt in adj[node]) {
            if (visited[nxt]) continue
            isLeaf = false
            dfs(nxt)
        }
        if (isLeaf) {
            leaf++
        }
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val n = st.nextToken().toInt()
        visited = BooleanArray(n + 1)
        adj = Array(n + 1) { mutableListOf() }
        w = st.nextToken().toInt()
        repeat(n - 1) {
            st = StringTokenizer(readLine(), " ")
            val u = st.nextToken().toInt()
            val v = st.nextToken().toInt()
            adj[u] += v
            adj[v] += u
        }
    }

    fun solution() {
        input()
        dfs(1)
        val result = String.format("%.10f", w / leaf)
        print(result)
    }
}

fun main() {
    `17073`().solution()
}