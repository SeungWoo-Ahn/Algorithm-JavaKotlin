package boj.floyd_warshall

class `1719` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        val map = Array(n + 1) { x ->
            IntArray(n + 1) { y -> if (x != y) INF else 0 }
        }
        val nxt = Array(n + 1) { IntArray(n + 1) }
        repeat(m) {
            val (u, v, cost) = readLine().splitToInt()
            map[u][v] = cost
            map[v][u] = cost
            nxt[u][v] = v
            nxt[v][u] = u
        }
        for (k in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1..n) {
                    if (map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j]
                        nxt[i][j] = nxt[i][k]
                    }
                }
            }
        }
        val sb = StringBuilder()
        for (i in 1 .. n) {
            for (j in 1 .. n) {
                if (i == j) sb.append("- ")
                else sb.append("${nxt[i][j]} ")
            }
            sb.append("\n")
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 90_000_000
    }
}

fun main() {
    `1719`().solution()
}