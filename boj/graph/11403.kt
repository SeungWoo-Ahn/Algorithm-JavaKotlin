package boj.graph

import java.lang.StringBuilder

class `11403` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val map = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        for (k in 0 until N) {
            for (i in 0 until N) {
                for (j in 0 until N) {
                    if (map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1
                }
            }
        }
        val sb = StringBuilder()
        for (i in 0 until N) {
            for (j in 0 until N) {
                sb.append("${map[i][j]} ")
            }
            sb.append("\n")
        }
        println(sb)
    }
}

fun main() {
    `11403`().solution()
}

