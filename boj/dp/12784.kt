package boj.dp

class `12784` {
    private data class Edge(val to: Int, val cost: Int)

    private lateinit var adj: Array<MutableList<Edge>>

    private fun dfs(cur: Int, parent: Int): Int {
        var total = 0
        for ((nxt, cost) in adj[cur]) {
            if (nxt == parent) continue
            val acc = dfs(nxt, cur)
            total += if (acc == 0) {
                cost
            } else {
                minOf(acc, cost)
            }
        }
        return total
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val (n, m) = readLine().splitToInt()
            adj = Array(n + 1) { mutableListOf() }
            repeat(m) {
                val (x, y, cost) = readLine().splitToInt()
                adj[x] += Edge(y, cost)
                adj[y] += Edge(x, cost)
            }
            sb.appendLine(dfs(1, 0))
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map(String::toInt)
}

fun main() {
    `12784`().solution()
}