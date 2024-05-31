package boj.floyd_warshall

class `11562` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        val map = Array(n + 1) { x ->
            IntArray(n + 1) { y -> if (x != y) INF else 0 }
        }
        repeat(m) {
            val (u, v, b) = readLine().splitToInt()
            map[u][v] = 0
            map[v][u] = if (b == 0) 1 else 0
        }
        for (via in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1 .. n) {
                    if (map[i][via] + map[via][j] < map[i][j]) {
                        map[i][j] = map[i][via] + map[via][j]
                    }
                }
            }
        }
        val k = readLine().toInt()
        val sb = StringBuilder()
        repeat(k) {
            val (s, e) = readLine().splitToInt()
            sb.appendLine(map[s][e])
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 1_000
    }
}

fun main() {
    `11562`().solution()
}