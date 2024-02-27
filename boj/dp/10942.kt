package boj.dp

import java.util.StringTokenizer

class `10942` {
    private val a = IntArray(2001)
    private val d = Array(2001) { IntArray(2001) }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        for (i in 1 .. n) {
            a[i] = st.nextToken().toInt()
            d[i][i] = 1
            if (a[i - 1] == a[i]) d[i - 1][i] = 1
        }
        for (gap in 2 until  n) {
            for (i in 1 .. n - gap) {
                val s = i
                val e = i + gap
                if (a[s] == a[e] && d[s + 1][e - 1] == 1) {
                    d[s][e] = 1
                }
            }
        }
        val m = readLine().toInt()
        val sb = StringBuilder()
        repeat(m) {
            val (s, e) = readLine().split(" ").map { it.toInt() }
            sb.append("${d[s][e]}\n")
        }
        println(sb)
    }
}

fun main() {
    `10942`().solution()
}