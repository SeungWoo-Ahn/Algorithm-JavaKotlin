package boj.backtracking

import java.lang.StringBuilder
import java.util.StringTokenizer

class `15663` {
    private var input = intArrayOf()
    private var arr = intArrayOf()
    private var visited = booleanArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(n: Int, m: Int, depth: Int) {
        if (depth == m) {
            arr.forEach { sb.append("$it ") }
            sb.append("\n")
            return
        }
        var temp = 0
        for (i in 0 until n) {
            if (visited[i] || temp == input[i]) continue
            input[i].let {
                arr[depth] = it
                temp = it
            }
            visited[i] = true
            backtracking(n, m, depth + 1)
            visited[i] = false
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        input = IntArray(N) { st.nextToken().toInt() }.apply { sort() }
        arr = IntArray(M)
        visited = BooleanArray(N)
        backtracking(N, M, 0)
        println(sb)
    }
}

fun main() {
    `15663`().solution()
}