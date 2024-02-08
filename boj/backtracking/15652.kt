package boj.backtracking

import java.lang.StringBuilder

class `15652` {
    private var arr = intArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(n: Int, m: Int, depth: Int, startIdx: Int) {
        if (depth == m) {
            arr.forEach { sb.append("$it ") }
            sb.append("\n")
            return
        }
        for (i in startIdx .. n) {
            arr[depth] = i
            backtracking(n, m, depth + 1, i)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        arr = IntArray(M)
        backtracking(N, M, 0, 1)
        println(sb)
    }
}

fun main() {
    `15652`().solution()
}