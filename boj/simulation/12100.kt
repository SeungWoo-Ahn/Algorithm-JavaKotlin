package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `12100` {
    private var n = 0
    private var maxBlock = 0L

    private fun backtracking(depth: Int, board: Array<LongArray>) {
        if (depth == 5) {
            findMaxBlock(board)
            return
        }
        for (i in 1 .. 4) {
            val copyBoard = Array(n) { idx -> board[idx].copyOf() }
            when (i) {
                1 -> moveLeft(copyBoard)
                2 -> moveRight(copyBoard)
                3 -> moveUp(copyBoard)
                4 -> moveDown(copyBoard)
            }
            backtracking(depth + 1, copyBoard)
        }
    }

    private fun moveLeft(board: Array<LongArray>) {
        for (i in 0 until n) {
            val q = LinkedList<Long>() as Queue<Long>
            for (j in 0 until n) {
                if (board[i][j] != 0L) {
                    q.offer(board[i][j])
                    board[i][j] = 0L
                }
            }
            var idx = 0
            while (q.isNotEmpty()) {
                val cur = q.poll()
                if (q.isNotEmpty() && cur == q.peek()) {
                    board[i][idx] = cur * 2
                    q.poll()
                } else {
                    board[i][idx] = cur
                }
                idx++
            }
        }
    }

    private fun moveRight(board: Array<LongArray>) {
        for (i in 0 until n) {
            val q = LinkedList<Long>() as Queue<Long>
            for (j in n - 1 downTo 0) {
                if (board[i][j] != 0L) {
                    q.offer(board[i][j])
                    board[i][j] = 0L
                }
            }
            var idx = n - 1
            while (q.isNotEmpty()) {
                val cur = q.poll()
                if (q.isNotEmpty() && cur == q.peek()) {
                    board[i][idx] = cur * 2
                    q.poll()
                } else {
                    board[i][idx] = cur
                }
                idx--
            }
        }
    }

    private fun moveUp(board: Array<LongArray>) {
        for (i in 0 until n) {
            val q = LinkedList<Long>() as Queue<Long>
            for (j in 0 until n) {
                if (board[j][i] != 0L) {
                    q.offer(board[j][i])
                    board[j][i] = 0
                }
            }
            var idx = 0
            while (q.isNotEmpty()) {
                val cur = q.poll()
                if (q.isNotEmpty() && cur == q.peek()) {
                    board[idx][i] = cur * 2
                    q.poll()
                } else {
                    board[idx][i] = cur
                }
                idx++
            }
        }
    }

    private fun moveDown(board: Array<LongArray>) {
        for (i in 0 until n) {
            val q = LinkedList<Long>() as Queue<Long>
            for (j in n - 1 downTo 0) {
                if (board[j][i] != 0L) {
                    q.offer(board[j][i])
                    board[j][i] = 0
                }
            }
            var idx = n - 1
            while (q.isNotEmpty()) {
                val cur = q.poll()
                if (q.isNotEmpty() && cur == q.peek()) {
                    board[idx][i] = cur * 2
                    q.poll()
                } else {
                    board[idx][i] = cur
                }
                idx--
            }
        }
    }

    private fun findMaxBlock(board: Array<LongArray>) {
        var max = 0L
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (board[i][j] > max) max = board[i][j]
            }
        }
        if (max > maxBlock) maxBlock = max
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()
        val board = Array(n) {
            val line = LongArray(n)
            val st = StringTokenizer(readLine())
            for (i in 0 until n) line[i] = st.nextToken().toLong()
            line
        }
        backtracking(0, board)
        println(maxBlock)
    }
}

fun main() {
    `12100`().solution()
}