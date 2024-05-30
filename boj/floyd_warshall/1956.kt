package boj.floyd_warshall

class `1956` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (v, e) = readLine().splitToInt()
        val map = Array(v + 1) { x ->
            IntArray(v + 1) { y -> if (x != y) INF else 0 }
        }
        repeat(e) {
            val (a, b, c) = readLine().splitToInt()
            map[a][b] = c
        }
        for (k in 1 .. v) {
            for (i in 1 .. v) {
                for (j in 1 .. v) {
                    map[i][j] = minOf(map[i][j], map[i][k] + map[k][j])
                }
            }
        }
        var answer = INF
        for (i in 1 .. v) {
            for (j in i + 1 .. v) {
                answer = minOf(answer, map[i][j] + map[j][i])
            }
        }
        if (answer == INF) println(-1)
        else println(answer)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 9_000_000
    }
}

fun main() {
    `1956`().solution()
}