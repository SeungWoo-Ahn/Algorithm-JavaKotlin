package boj.backtracking

import java.lang.StringBuilder

class `15651` {
    private var arr = intArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(n: Int, m: Int, depth: Int) {
        if (depth == m) {
            arr.forEach { sb.append("$it ") }
            sb.append("\n")
            return
        }
        for (i in 1 .. n) {
            arr[depth] = i
            backtracking(n, m, depth + 1)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        arr = IntArray(M)
        backtracking(N, M, 0)
        println(sb)
    }
}

fun main() {
    `15651`().solution()
}