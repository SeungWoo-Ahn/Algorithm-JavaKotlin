package boj.backtracking

import kotlin.system.exitProcess

class `2239` {
    private data class Node(val x: Int, val y: Int)

    private val board = Array(9) { IntArray(9) }
    private val rowExist = Array(9) { BooleanArray(10) }
    private val colExist = Array(9) { BooleanArray(10) }
    private val boxExist = Array(9) { BooleanArray(10) }
    private val unknown = mutableListOf<Node>()

    private fun boxIdx(x: Int, y: Int): Int {
        return (x / 3) * 3 + (y / 3)
    }

    private fun backtracking(idx: Int) {
        if (idx == unknown.size) {
            printBoard()
            exitProcess(0)
        }
        for (i in 1..9) {
            val (x, y) = unknown[idx]
            if (!rowExist[x][i] && !colExist[y][i] && !boxExist[boxIdx(x, y)][i]) {
                board[x][y] = i
                rowExist[x][i] = true
                colExist[y][i] = true
                boxExist[boxIdx(x, y)][i] = true
                backtracking(idx + 1)
                board[x][y] = 0
                rowExist[x][i] = false
                colExist[y][i] = false
                boxExist[boxIdx(x, y)][i] = false
            }
        }
    }

    private fun printBoard() {
        val sb = StringBuilder()
        for (x in 0 until 9) {
            for (y in 0 until 9) {
                sb.append(board[x][y])
            }
            sb.append("\n")
        }
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        repeat(9) { x ->
            val line = readLine()
            repeat(9) { y ->
                val num = line[y] - '0'
                board[x][y] = num
                if (num == 0) unknown += Node(x, y)
                else {
                    rowExist[x][num] = true
                    colExist[y][num] = true
                    boxExist[boxIdx(x, y)][num] = true
                }
            }
        }
        backtracking(0)
    }
}

fun main() {
    `2239`().solution()
}