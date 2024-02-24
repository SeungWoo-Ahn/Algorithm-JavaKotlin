package boj.dp

import java.util.ArrayDeque
import java.util.StringTokenizer

class `14002` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val a = IntArray(1010)
        val d = IntArray(1010) { 1 }
        val pre = IntArray(1010)
        val st = StringTokenizer(readLine())
        for (i in 1 .. n) a[i] = st.nextToken().toInt()

        var max = d[1]
        var maxIdx = 1
        for (i in 1 .. n) {
            for (j in 1 until i) {
                if (a[j] < a[i] && d[i] < d[j] + 1) {
                    d[i] = d[j] + 1
                    pre[i] = j
                }
            }
            if (d[i] > max) {
                max = d[i]
                maxIdx =i
            }
        }
        val q = ArrayDeque<Int>()
        var curIdx = maxIdx
        while (curIdx != 0) {
            q.addFirst(a[curIdx])
            curIdx = pre[curIdx]
        }
        println("$max\n${q.joinToString(" ")}")
    }
}

fun main() {
    `14002`().solution()
}