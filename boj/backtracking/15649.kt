package boj.backtracking

import java.lang.StringBuilder

class `15649` {
    private var arr = intArrayOf()
    private var visited = booleanArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(n: Int, m: Int, depth: Int) {
        if (depth == m) {
            sb.append("${arr.joinToString(" ")}\n")
            return
        }
        for (i in 1 .. n) {
            if (!visited[i -1]) {
                arr[depth] = i
                visited[i - 1] = true
                backtracking(n, m, depth + 1)
                visited[i - 1] = false
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        visited = BooleanArray(N)
        arr = IntArray(M)
        backtracking(N, M, depth = 0)
        println(sb)
    }
}

fun main() {
    `15649`().solution()
}