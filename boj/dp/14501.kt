package boj.dp

import kotlin.math.max

class `14501` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val input = Array(n) { IntArray(2) }
        val d = IntArray(n)
        for (i in 0 until n) {
            val (t, p) = readLine().split(" ").map { it.toInt() }
            input[i][0] = t
            input[i][1] = p
            if (i + t <= n) d[i] = p
        }
        for (i in 0 until n) {
            for (j in i + input[i][0] until n) {
                if (j + input[j][0] <= n) {
                    d[j] = max(d[j], d[i] + input[j][1])
                }
            }
        }
        print(d.max())
    }
}

fun main() {
    `14501`().solution()
}