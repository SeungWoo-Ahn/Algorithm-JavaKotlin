package boj.backtracking

import java.lang.StringBuilder

class `15650` {
    private var arr = intArrayOf()
    private var visited = booleanArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(n: Int, m: Int, depth: Int, startIdx: Int) {
        if (depth == m) {
            sb.append("${arr.joinToString(" ")}\n")
            return
        }
        for (i in 1 .. n) {
            if (depth != 0 && i <= startIdx) continue
            if (visited[i - 1]) continue
            arr[depth] = i
            visited[i - 1] = true
            backtracking(n, m, depth + 1, i)
            visited[i - 1] = false
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        visited = BooleanArray(N)
        arr = IntArray(M)
        backtracking(N, M, 0, 1)
        println(sb)
    }
}

fun main() {
    `15650`().solution()
}