package boj.dp

import java.util.StringTokenizer

class `4883` {
    private val a = Array(100_001) { IntArray(3) }
    private val d = Array(100_001) { IntArray(3) }

    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        var t = 1
        while (true) {
            val n = readLine().toInt()
            if (n == 0) break
            for (i in 1 .. n) {
                val st = StringTokenizer(readLine())
                for (j in 0 until 3) {
                    a[i][j] = st.nextToken().toInt()
                }
            }
            d[1][0] = 1001
            d[1][1] = a[1][1]
            d[1][2] = a[1][1] + a[1][2]
            for (i in 2 .. n) {
                d[i][0] = a[i][0] + minOf(d[i - 1][0], d[i - 1][1])
                d[i][1] = a[i][1] + minOf(d[i - 1][0], d[i - 1][1], d[i - 1][2], d[i][0])
                d[i][2] = a[i][2] + minOf(d[i - 1][1], d[i - 1][2], d[i][1])
            }
            sb.append("${t++}. ${d[n][1]}\n")
        }
        println(sb)
    }
}

fun main() {
    `4883`().solution()
}