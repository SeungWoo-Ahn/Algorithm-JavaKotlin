package boj.dp

import java.lang.StringBuilder

class `1003` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val input = IntArray(n)
        var max = 0
        for (i in 0 until n) {
            input[i] = readLine().toInt()
            if (input[i] > max) max = input[i]
        }
        val d = Array(41) { IntArray(2) }
        d[0][0] = 1.also { d[0][1] = 0 }
        d[1][0] = 0.also { d[1][1] = 1 }
        for (i in 2 .. max) {
            d[i][0] = d[i - 1][0] + d[i - 2][0]
            d[i][1] = d[i - 1][1] + d[i - 2][1]
        }
        val sb = StringBuilder()
        for (i in input.indices) {
            input[i].let {
                sb.append("${d[it][0]} ${d[it][1]}\n")
            }
        }
        println(sb)
    }
}

fun main() {
    `1003`().solution()
}