package boj.dp

import java.lang.StringBuilder

class `9461` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val input = IntArray(n) { readLine().toInt() }
        val max = input.max()
        val d = LongArray(101)
        d[1] = 1
        d[2] = 1
        d[3] = 1
        d[4] = 2
        d[5] = 2
        for (i in 6 .. max) {
            d[i] = d[i - 1] + d[i - 5]
        }
        val sb = StringBuilder()
        for (i in 0 until n) {
            sb.append("${d[input[i]]}\n")
        }
        print(sb)
    }
}

fun main() {
    `9461`().solution()
}