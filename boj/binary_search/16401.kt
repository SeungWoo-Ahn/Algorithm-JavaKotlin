package boj.binary_search

import java.util.StringTokenizer

class `16401` {
    private var arr = intArrayOf()

    private fun solve(m: Int, x: Int): Boolean {
        if (x == 0) return true
        var cnt = 0L
        for (a in arr) {
            cnt += a / x
        }
        return cnt >= m
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (m, n) = readLine().split(" ").map { it.toInt() }
        arr = IntArray(n)
        val st = StringTokenizer(readLine())
        repeat(n) { arr[it] = st.nextToken().toInt() }
        var s = 0
        var e = arr.max()
        while (s < e) {
            val mid = (s + e + 1) / 2
            if (solve(m, mid)) s = mid
            else e = mid - 1
        }
        println(s)
    }
}

fun main() {
    `16401`().solution()
}