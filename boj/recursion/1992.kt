package boj.recursion

import java.lang.StringBuilder

class `1992` {
    private var video: Array<CharArray> = arrayOf()
    private val sb = StringBuilder()

    private fun recursion(x: Int, y: Int, n: Int) {
        if (check(x, y, n)) {
            sb.append(video[x][y])
            return
        }
        val s = n / 2
        sb.append("(")
        for (i in 0 until 2) {
            for (j in 0 until 2) {
                recursion(x + i * s, y + j * s, s)
            }
        }
        sb.append(")")
    }

    private fun check(x: Int, y: Int, n: Int): Boolean {
        for (i in x until x + n) {
            for (j in y until y + n) {
                if (video[i][j] != video[x][y])
                    return false
            }
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        video = Array(n) { readLine().toCharArray() }
        recursion(0, 0, n)
        println(sb)
    }
}

fun main() {
    `1992`().solution()
}
