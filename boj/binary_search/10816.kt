package boj.binary_search

import java.util.StringTokenizer

class `10816` {
    private var arr = intArrayOf()

    private fun lowerIdx(target: Int, len: Int): Int {
        var s = 0
        var e = len
        while (s < e) {
            val mid = (s + e) / 2
            if (arr[mid] >= target) e = mid
            else s = mid + 1
        }
        return s
    }

    private fun upperIdx(target: Int, len: Int): Int {
        var s = 0
        var e = len
        while (s < e) {
            val mid = (s + e) / 2
            if (arr[mid] > target) e = mid
            else s = mid + 1
        }
        return s
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        arr = IntArray(n)
        var st = StringTokenizer(readLine())
        for (i in 0 until n) arr[i] = st.nextToken().toInt()
        arr.sort()
        var m = readLine().toInt()
        st = StringTokenizer(readLine())
        val sb = StringBuilder()
        while (m-- > 0) {
            val target = st.nextToken().toInt()
            sb.append("${upperIdx(target, n) - lowerIdx(target, n)} ")
        }
        println(sb)
    }
}

fun main() {
    `10816`().solution()
}