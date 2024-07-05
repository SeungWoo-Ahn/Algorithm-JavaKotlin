package boj.bellman_ford

class `1865` {
    private data class Edge(val to: Int, val weight: Int)

    private var graph: Array<MutableList<Edge>> = arrayOf()
    private var dis = intArrayOf()

    private fun bellmanFord(n: Int): String {
        dis[1] = 0
        var updated = false
        for (repeat in 1 until n) {
            updated = false
            for (i in 1 .. n) {
                for ((to, weight) in graph[i]) {
                    if (dis[i] + weight < dis[to]) {
                        dis[to] = dis[i] + weight
                        updated = true
                    }
                }
            }
            if (!updated) break
        }
        return if (updated && negativeCycleExist(n)) "YES" else "NO"
    }

    private fun negativeCycleExist(n: Int): Boolean {
        for (i in 1 .. n) {
            for ((to, weight) in graph[i]) {
                if (dis[i] + weight < dis[to])
                    return true
            }
        }
        return false
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val tc = readLine().toInt()
        val sb = StringBuilder()
        repeat(tc) {
            val (n, m, w) = readLine().splitToInt()
            dis = IntArray(n + 1) { INF }
            graph = Array(n + 1) { mutableListOf() }
            repeat(m) {
                val (s, e, t) = readLine().splitToInt()
                graph[s] += Edge(e, t)
                graph[e] += Edge(s, t)
            }
            repeat(w) {
                val (s, e, t) = readLine().splitToInt()
                graph[s] += Edge(e, -t)
            }
            sb.appendLine(bellmanFord(n))
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 10_001
    }
}

fun main() {
    `1865`().solution()
}