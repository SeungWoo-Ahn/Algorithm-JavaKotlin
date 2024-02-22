package boj.dp

import java.util.StringTokenizer
import kotlin.math.min

class `1149` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val costs = Array(n + 1) { IntArray(3) }
        var st: StringTokenizer
        for (i in 1 .. n) {
            st = StringTokenizer(readLine())
            for (j in 0 until 3) {
                costs[i][j] = st.nextToken().toInt()
            }
        }
        val d = Array(n + 1) { IntArray(3) }
        d[1][0] = costs[1][0]
        d[1][1] = costs[1][1]
        d[1][2] = costs[1][2]
        for (i in 2 .. n) {
            d[i][0] = min(d[i - 1][1], d[i - 1][2]) + costs[i][0]
            d[i][1] = min(d[i - 1][2], d[i - 1][0]) + costs[i][1]
            d[i][2] = min(d[i - 1][0], d[i - 1][1]) + costs[i][2]
        }
        println(min(d[n][0], min(d[n][1], d[n][2])))
    }
}

fun main() {
    `1149`().solution()
}