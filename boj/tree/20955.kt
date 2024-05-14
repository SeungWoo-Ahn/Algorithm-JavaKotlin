package boj.tree

class `20955` {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var visited = booleanArrayOf()

    private fun dfs(cur: Int) {
        if (visited[cur]) return
        visited[cur] = true
        for (nxt in adj[cur])
            dfs(nxt)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        adj = Array(n + 1) { mutableListOf() }
        visited = BooleanArray(n + 1)
        repeat(m) {
            val (u, v) = readLine().splitToInt()
            adj[u].add(v)
            adj[v].add(u)
        }
        var groupCnt = 0
        for (i in 1 .. n) {
            if (visited[i]) continue
            dfs(i)
            groupCnt++
        }
        println((groupCnt - 1) + (m + groupCnt - 1) - (n - 1))
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `20955`().solution()
}