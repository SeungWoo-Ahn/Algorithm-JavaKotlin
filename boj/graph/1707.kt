package boj.graph

class `1707` {
    private var graph: Array<MutableList<Int>> = arrayOf()
    private var colors = intArrayOf()
    private var checkBipartiteGraph = true

    private fun dfs(cur: Int, color: Int) {
        colors[cur] = color
        for (adj in graph[cur]) {
            if (colors[adj] == color) {
                checkBipartiteGraph = false
                break
            }
            if (colors[adj] == NONE)
                dfs(adj, -color)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val k = readLine().toInt()
        val sb = StringBuilder()
        repeat(k) {
            val (V, E) = readLine().splitToInt()
            graph = Array(V + 1) { mutableListOf() }
            colors = IntArray(V + 1)
            checkBipartiteGraph = true
            repeat(E) {
                val (u, v) = readLine().splitToInt()
                graph[u].add(v)
                graph[v].add(u)
            }
            for (i in 1 .. V) {
                if (!checkBipartiteGraph)
                    break
                if (colors[i] == NONE)
                    dfs(i, RED)
            }
            sb.appendLine(
                if (checkBipartiteGraph) "YES"
                else "NO"
            )
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val RED = -1
        private const val NONE = 0
        private const val BLUE = 1
    }
}

fun main() {
    `1707`().solution()
}