package boj.dp

import java.lang.StringBuilder
import java.util.StringTokenizer

class `11659` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val arr = IntArray(n + 1)
        val d = IntArray(n + 1)
        val st = StringTokenizer(readLine())
        for (i in 1 .. n) {
            arr[i] = st.nextToken().toInt()
            d[i] = d[i - 1] + arr[i]
        }
        val sb = StringBuilder()
        repeat(m) {
            val (i, j) = readLine().split(" ").map { it.toInt() }
            sb.append("${d[j] - d[i - 1]}\n")
        }
        println(sb)
    }
}

fun main() {
    `11659`().solution()
}