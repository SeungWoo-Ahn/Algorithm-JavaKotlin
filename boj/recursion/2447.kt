package boj.recursion

import java.lang.StringBuilder

class `2447` {
    private var board: Array<CharArray> = arrayOf()

    private fun recursion(n: Int, x: Int, y: Int) {
        if (n == 1) {
            board[x][y] = '*'
            return
        }
        val s = n / 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (i == 1 && j == 1) continue
                recursion(s, x + i * s, y + j * s)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        board = Array(n) { CharArray(n) { ' ' } }
        recursion(n, 0, 0)
        val sb = StringBuilder()
        for (i in 0 until n) {
            for (j in 0 until n) {
                sb.append(board[i][j])
            }
            sb.append("\n")
        }
        println(sb)
    }
}

fun main() {
    `2447`().solution()
}