package boj.backtracking

import kotlin.system.exitProcess

class `2239` {
    private data class Node(val x: Int, val y: Int)

    private val board = Array(9) { IntArray(9) }
    private val unknown = mutableListOf<Node>()

    private fun isPossible(x: Int, y: Int): Boolean {
        for (i in 0 until 9) {
            if (i != x && board[i][y] == board[x][y]) {
                return false
            }
            if (i != y && board[x][i] == board[x][y]) {
                return false
            }
        }
        val sX = (x / 3) * 3
        val sY = (y / 3) * 3
        for (i in sX until sX + 3) {
            for (j in sY until sY + 3) {
                if (i == x && j == y) continue
                if (board[i][j] == board[x][y])
                    return false
            }
        }
        return true
    }

    private fun backtracking(idx: Int) {
        if (idx == unknown.size) {
            printBoard()
            exitProcess(0)
        }
        for (i in 1..9) {
            val (x, y) = unknown[idx]
            board[x][y] = i
            if (isPossible(x, y)) backtracking(idx + 1)
            board[x][y] = 0
        }
    }

    private fun printBoard() {
        val sb = StringBuilder()
        for (x in 0 until 9) {
            for (y in 0 until 9) {
                sb.append(board[x][y])
            }
            if (x < 8) {
                sb.append("\n")
            }
        }
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        repeat(9) { x ->
            val line = readLine().toCharArray()
            repeat(9) { y ->
                board[x][y] = line[y] - '0'
                if (board[x][y] == 0) {
                    unknown += Node(x, y)
                }
            }
        }
        backtracking(0)
    }
}

fun main() {
    `2239`().solution()
}