package boj.backtracking

import java.util.StringTokenizer

class `1182` {
    private var arr = intArrayOf()
    private var cnt = 0

    private fun backtracking(n: Int, s: Int, depth: Int, total: Int) {
        if (depth == n) {
            if (total == s) cnt++
            return
        }
        backtracking(n, s, depth + 1, total)
        backtracking(n, s, depth + 1, total + arr[depth])
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, S) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        arr = IntArray(N) { st.nextToken().toInt() }
        backtracking(N, S, 0, 0)
        if (S == 0) cnt--
        println(cnt)
    }
}

fun main() {
    `1182`().solution()
}