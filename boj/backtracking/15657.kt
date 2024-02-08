package boj.backtracking

import java.lang.StringBuilder
import java.util.StringTokenizer

class `15657` {
    private var input = intArrayOf()
    private var arr = intArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(n: Int, m: Int, depth: Int, startIdx: Int) {
        if (depth == m) {
            arr.forEach { sb.append("$it ") }
            sb.append("\n")
            return
        }
        for (i in startIdx until n) {
            arr[depth] = input[i]
            backtracking(n, m, depth + 1, i)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        input = IntArray(N) { st.nextToken().toInt() }.apply { sort() }
        arr = IntArray(M)
        backtracking(N, M, 0, 0)
        println(sb)
    }
}

fun main() {
    `15657`().solution()
}