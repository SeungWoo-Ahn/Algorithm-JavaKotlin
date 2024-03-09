package boj.binary_search

import java.util.StringTokenizer

class `2805` {
    private var arr = intArrayOf()

    private fun solve(x: Int, m: Int): Boolean {
        var total = 0L
        for (height in arr) {
            if (height - x > 0) {
                total += height - x
            }
        }
        return total >= m
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        arr = IntArray(n)
        var maxHeight = 0
        val st = StringTokenizer(readLine())
        repeat(n) {
            arr[it] = st.nextToken().toInt()
            if (arr[it] > maxHeight) maxHeight = arr[it]
        }
        var s = 0
        var e = maxHeight
        while (s < e) {
            val mid = (s + e + 1) / 2
            if (solve(mid, m)) s = mid
            else e = mid - 1
        }
        println(s)
    }
}

fun main() {
    `2805`().solution()
}