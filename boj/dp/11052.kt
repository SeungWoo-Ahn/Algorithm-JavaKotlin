package boj.dp

import java.util.StringTokenizer
import kotlin.math.max

class `11052` {
    private val d = IntArray(1001)

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        for (i in 1 .. n) { d[i] = st.nextToken().toInt() }
        for (i in 1 .. n) {
            for (j in 1 until i) {
                d[i] = max(d[i], d[i - j] + d[j])
            }
        }
        println(d[n])
    }
}

fun main() {
    `11052`().solution()
}