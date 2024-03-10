package boj.binary_search

import java.util.StringTokenizer

class `3151` {
    private var arr = intArrayOf()

    private fun upperBound(start: Int, target: Int): Int {
        var s = start
        var e = arr.size
        while (s < e) {
            val mid = s + (e - s) / 2
            if (arr[mid] <= target) s = mid + 1
            else e = mid
        }
        return s
    }

    private fun lowerBound(start: Int, target: Int): Int {
        var s = start
        var e = arr.size
        while (s < e) {
            val mid = s + (e - s) / 2
            if (arr[mid] < target) s = mid + 1
            else e = mid
        }
        return s
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        arr = IntArray(n)
        val st = StringTokenizer(readLine())
        repeat(n) { arr[it] = st.nextToken().toInt() }
        arr.sort()
        var answer = 0L
        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                val upperBound = upperBound(j + 1, -arr[i] - arr[j])
                val lowerBound = lowerBound(j + 1, -arr[i] - arr[j])
                answer += upperBound - lowerBound
            }
        }
        println(answer)
    }
}

fun main() {
    `3151`().solution()
}