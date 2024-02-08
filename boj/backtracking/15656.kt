package boj.backtracking

import java.lang.StringBuilder
import java.util.StringTokenizer

class `15656` {
    private var input = intArrayOf()
    private var arr = intArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(n: Int, m: Int, depth: Int) {
        if (depth == m) {
            arr.forEach { sb.append("$it ") }
            sb.append("\n")
            return
        }
        for (i in 0 until n) {
            arr[depth] = input[i]
            backtracking(n, m, depth + 1)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        input = IntArray(N) { st.nextToken().toInt() }.apply { sort() }
        arr = IntArray(M)
        backtracking(N, M, 0)
        println(sb)
    }
}

fun main() {
    `15656`().solution()
}