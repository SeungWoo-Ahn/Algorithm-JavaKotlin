package boj.binary_search

import java.util.*

class `18869` {
    private fun compress(a: IntArray) {
        val set = TreeSet<Int>()
        for (i in a) set.add(i)
        val v = set.toIntArray()
        for (i in a.indices) {
            a[i] = v.binarySearch(a[i])
        }
    }

    private fun compare(a: IntArray, b: IntArray): Int {
        for (i in a.indices) {
            if (a[i] != b[i]) return 0
        }
        return 1
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (m, n) = readLine().split(" ").map { it.toInt() }
        val arr = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            val st = StringTokenizer(readLine())
            for (j in 0 until n) {
                arr[i][j] = st.nextToken().toInt()
            }
            compress(arr[i])
        }
        var answer = 0
        for (i in 0 until m - 1) {
            for (j in i + 1 until m) {
                answer += compare(arr[i], arr[j])
            }
        }
        println(answer)
    }
}

fun main() {
    `18869`().solution()
}