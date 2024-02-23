package boj.dp

import kotlin.math.max

class `15486` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val input = Array(1500005) { IntArray(2) }
        val d = IntArray(1500005)
        for (i in 0 until n) {
            val (t, p) = readLine().split(" ").map { it.toInt() }
            input[i][0] = t
            input[i][1] = p
        }
        for (i in n - 1 downTo 0) {
            if (i + input[i][0] <= n) {
                d[i] = max(d[i + input[i][0]] + input[i][1], d[i + 1])
            } else d[i] = d[i + 1]
        }
        var max = 0
        for (i in 0 until n) {
            if (d[i] > max) max = d[i]
        }
        print(max)
    }
}

fun main() {
    `15486`().solution()
}