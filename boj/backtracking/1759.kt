package boj.backtracking

import java.lang.StringBuilder
import java.util.StringTokenizer

class `1759` {
    private var input = arrayOf<String>()
    private var arr = arrayOf<String>()
    private val sb = StringBuilder()

    private fun backtracking(l: Int, c: Int, depth: Int, startIdx: Int) {
        if (depth == l) {
            if (check(l)) {
                arr.forEach { sb.append(it) }
                sb.append("\n")
            }
            return
        }
        for (i in startIdx until c) {
            arr[depth] = input[i]
            backtracking(l, c, depth + 1, i + 1)
        }
    }

    private fun check(l: Int): Boolean {
        var cnt = 0
        arr.forEach {
            if (it == "a" || it == "e" || it == "i" || it == "o" || it == "u")
                cnt++
        }
        return cnt >= 1 && (l - cnt) >= 2
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (L, C) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        input = Array(C) { st.nextToken() }.apply { sort() }
        arr = Array(L) { "" }
        backtracking(L, C, 0, 0)
        println(sb)
    }
}

fun main() {
    `1759`().solution()
}