package boj.tree

class `4803` {
    private var graph: Array<MutableList<Int>> = arrayOf()
    private var visited = booleanArrayOf()
    private var isTree = true

    private fun dfs(cur: Int, prev: Int) {
        for (adj in graph[cur]) {
            if (adj == prev) continue
            if (visited[adj]) {
                isTree = false
                continue
            }
            visited[adj] = true
            dfs(adj, cur)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        var caseNum = 1
        while (true) {
            val (n, m) = readLine().splitToInt()
            if (n == 0 && m == 0) break
            graph = Array(n + 1) { mutableListOf() }
            visited = BooleanArray(n + 1)
            repeat(m) {
                val (u, v) = readLine().splitToInt()
                graph[u].add(v)
                graph[v].add(u)
            }
            var cnt = 0
            for (i in 1 .. n) {
                if (visited[i]) continue
                visited[i] = true
                isTree = true
                dfs(i, -1)
                if (isTree) cnt++
            }
            sb.appendLine(
                when (cnt) {
                    0 -> "Case ${caseNum}: No trees."
                    1 -> "Case ${caseNum}: There is one tree."
                    else -> "Case ${caseNum}: A forest of $cnt trees."
                }
            )
            caseNum++
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `4803`().solution()
}