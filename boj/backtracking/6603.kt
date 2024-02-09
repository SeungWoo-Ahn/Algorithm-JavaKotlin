package boj.backtracking

import java.lang.StringBuilder
import java.util.StringTokenizer

class `6603` {
    private var s = intArrayOf()
    private var arr = intArrayOf()
    private val sb = StringBuilder()

    private fun backtracking(k: Int, depth: Int, startIdx: Int) {
        if (depth == 6) {
            arr.forEach { sb.append("$it ") }
            sb.append("\n")
            return
        }
        for (i in startIdx until k) {
            arr[depth] = s[i]
            backtracking(k, depth + 1, i + 1)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        while (true) {
            val st = StringTokenizer(readLine())
            val k = st.nextToken().toInt()
            if (k == 0) break
            s = IntArray(k) { st.nextToken().toInt() }
            arr = IntArray(6)
            backtracking(k, 0, 0)
            sb.append("\n")
        }
        println(sb)
    }
}

fun main() {
    `6603`().solution()
}