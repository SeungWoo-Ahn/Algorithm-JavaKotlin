package boj.math

import java.util.StringTokenizer

class `17504` {
    private var p = 1L
    private var q = 0L

    private fun flip() {
        val temp = p
        p = q
        q = temp
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val a = IntArray(n) { st.nextToken().toInt() }
        q = a[n - 1].toLong()
        for (i in n - 2 downTo 0) {
            p += a[i] * q
            flip()
        }
        p = q - p
        print("$p $q")
    }
}

fun main() {
    `17504`().solution()
}