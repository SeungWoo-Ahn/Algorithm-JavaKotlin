package boj.floyd_warshall

import kotlin.math.min

class `11780` {
    private var map: Array<IntArray> = arrayOf()
    private var nxt: Array<IntArray> = arrayOf()
    private val sb = StringBuilder()

    private fun floyd(n: Int) {
        for (k in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1 .. n) {
                    if (map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j]
                        nxt[i][j] = nxt[i][k]
                    }
                }
            }
        }
    }

    private fun saveShortest(n: Int) {
        for (i in 1 .. n) {
            for (j in 1 .. n) {
                if (map[i][j] == INF) sb.append("0 ")
                else sb.append("${map[i][j]} ")
            }
            sb.append("\n")
        }
    }

    private fun saveRoute(n: Int) {
        for (i in 1 .. n) {
            for (j in 1 .. n) {
                if (map[i][j] == 0 || map[i][j] == INF) {
                    sb.appendLine(0)
                    continue
                }
                val route = mutableListOf<Int>()
                var cur = i
                while (cur != j) {
                    route.add(cur)
                    cur = nxt[cur][j]
                }
                route.add(j)
                sb.appendLine("${route.size} ${route.joinToString(" ")}")
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        map = Array(n + 1) { x ->
            IntArray(n + 1) { y -> if (x != y) INF else 0 }
        }
        nxt = Array(n + 1) { IntArray(n + 1) }
        repeat(m) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            map[a][b] = min(map[a][b], c)
            nxt[a][b] = b
        }
        floyd(n)
        saveShortest(n)
        saveRoute(n)
        print(sb)
    }

    companion object {
        private const val INF = 100_001
    }
}

fun main() {
    `11780`().solution()
}