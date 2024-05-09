package boj.tree

class `11725` {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var parent = intArrayOf()

    private fun dfs(cur: Int) {
        for (nxt in adj[cur]) {
            if (parent[cur] == nxt) continue
            parent[nxt] = cur
            dfs(nxt)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        adj = Array(n + 1) { mutableListOf() }
        parent = IntArray(n + 1)
        repeat(n - 1) {
            val (u, v) = readLine().split(" ").map { it.toInt() }
            adj[u].add(v)
            adj[v].add(u)
        }
        dfs(1)
        val sb = StringBuilder()
        for (i in 2 .. n)
            sb.appendLine(parent[i])
        println(sb)
    }
}

fun main() {
    `11725`().solution()
}