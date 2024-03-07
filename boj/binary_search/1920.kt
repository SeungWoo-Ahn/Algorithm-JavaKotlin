package boj.binary_search

import java.util.StringTokenizer

class `1920` {
    private var arr = intArrayOf()

    private fun binarySearch(n: Int, target: Int): Int{
        var s = 0
        var e = n - 1
        while (s <= e) {
            val mid = (s + e) / 2
            if (arr[mid] < target) {
                s = mid + 1
            } else if (arr[mid] > target) {
                e = mid - 1
            } else
                return 1
        }
        return 0
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
            sb.append("${binarySearch(n, target)}\n")
        }
        println(sb)
    }
}

fun main() {
    `1920`().solution()
}