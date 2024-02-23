package boj.dp

import java.util.StringTokenizer
import kotlin.math.max

class `1932` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val triangle = Array(n + 1) { IntArray(n + 1) }
        for (i in 1 .. n) {
            val st = StringTokenizer(readLine())
            for (j in 1 .. i) {
                triangle[i][j] = st.nextToken().toInt()
            }
        }
        val d = Array(n + 1) { IntArray(n + 1) }
        d[1][1] = triangle[1][1]
        for (i in 2 .. n) {
            for (j in 1 .. i) {
                d[i][j] = when (j) {
                    1 -> d[i - 1][1]
                    i -> d[i - 1][j - 1]
                    else -> max(d[i - 1][j - 1], d[i - 1][j])
                }
                d[i][j] += triangle[i][j]
            }
        }
        var max = d[1][1]
        for (i in 1 .. n) {
            if (d[n][i] > max) max = d[n][i]
        }
        print(max)
    }
}

fun main() {
    `1932`().solution()
}