package boj.binary_search

import java.util.StringTokenizer

class `18870` {
    private val temp = mutableListOf<Int>()
    private val arr = mutableListOf<Int>()

    private fun lowerIdx(target: Int): Int {
        var s = 0
        var e = arr.size
        while (s < e) {
            val mid = (s + e) / 2
            if (arr[mid] >= target) e = mid
            else s = mid + 1
        }
        return s
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val input = IntArray(n)
        val st = StringTokenizer(readLine())
        repeat(n) { i ->
            input[i] = st.nextToken().toInt()
            temp.add(input[i])
        }
        temp.sort()
        for (i in temp.indices) {
            if (i == 0 || temp[i - 1] != temp[i]) {
                arr.add(temp[i])
            }
        }
        val sb = StringBuilder()
        for (target in input) {
            sb.append("${lowerIdx(target)} ")
        }
        println(sb)
    }
}

fun main() {
    `18870`().solution()
}