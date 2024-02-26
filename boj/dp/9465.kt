package boj.dp

import java.lang.StringBuilder
import java.util.StringTokenizer
import kotlin.math.max

class `9465` {
    private val price = Array(100_001) { IntArray(2) }
    private val d = Array(100_001) { IntArray(2) }

    fun solution() = with(System.`in`.bufferedReader()) {
        var t = readLine().toInt()
        val sb = StringBuilder()
        while (t-- > 0) {
            val n = readLine().toInt()
            for (j in 0 .. 1) {
                val st = StringTokenizer(readLine())
                for (i in 1 .. n) {
                    price[i][j] = st.nextToken().toInt()
                }
            }
            d[1][0] = price[1][0]
            d[1][1] = price[1][1]
            d[2][0] = price[2][0] + price[1][1]
            d[2][1] = price[2][1] + price[1][0]
            for (i in 3 .. n) {
                for (j in 0 .. 1) {
                    d[i][j] = max(d[i - 1][1 - j], max(d[i - 2][j], d[i - 2][1 - j])) + price[i][j]
                }
            }
            sb.append("${max(d[n][0], d[n][1])}\n")
        }
        println(sb)
    }
}

fun main() {
    `9465`().solution()
}