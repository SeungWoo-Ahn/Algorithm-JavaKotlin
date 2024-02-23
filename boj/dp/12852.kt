package boj.dp

import java.lang.StringBuilder

class `12852` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val d = IntArray(n + 1)
        val pre = IntArray(n + 1)
        for (i in 2 .. n) {
            d[i] = d[i - 1] + 1
            pre[i] = i - 1
            if (i % 3 == 0 && d[i] > d[i / 3] + 1) {
                d[i] = d[i / 3] + 1
                pre[i] = i / 3
            }
            if (i % 2 == 0 && d[i] > d[i / 2] + 1) {
                d[i] = d[i / 2] + 1
                pre[i] = i / 2
            }
        }
        val sb = StringBuilder()
        sb.append("${d[n]}\n")
        var curIdx = n
        while (true) {
            sb.append("$curIdx ")
            if (curIdx == 1) break
            curIdx = pre[curIdx]
        }
        println(sb)
    }
}

fun main() {
    `12852`().solution()
}