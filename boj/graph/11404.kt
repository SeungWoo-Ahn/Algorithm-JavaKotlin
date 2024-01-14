package boj.graph

import java.lang.StringBuilder
import kotlin.math.min

class `11404` {
    private val INF = 100 * 100_000

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        val map = Array(n + 1) { IntArray(n + 1) { INF } }.apply {
            repeat(m) {
                val (a, b, c) = readLine().split(" ").map { it.toInt() }
                this[a][b] = min(this[a][b], c)
            }
            for (i in 1 .. n) this[i][i] = 0
        }
        for (k in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1 .. n) {
                    map[i][j] = min(map[i][j], map[i][k] + map[k][j])
                }
            }
        }
        val sb = StringBuilder()
        for (i in 1 .. n) {
            for (j in 1 .. n) {
                if (map[i][j] == INF) sb.append("0 ")
                else sb.append("${map[i][j]} ")
            }
            sb.append("\n")
        }
        println(sb)
    }
}

fun main() {
    `11404`().solution()
}