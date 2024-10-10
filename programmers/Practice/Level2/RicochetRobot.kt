package programmers.Practice.Level2

import java.util.LinkedList
import java.util.Queue

class RicochetRobot {
    private data class Node(val x: Int, val y: Int, val pathCnt: Int)

    private val startPos = IntArray(2)
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun findStartPos(board: Array<String>) {
        for (x in board.indices) {
            for (y in board[x].indices) {
                if (board[x][y] == 'R') {
                    startPos[0] = x
                    startPos[1] = y
                }
            }
        }
    }

    private fun canMoveNext(
        x: Int,
        y: Int,
        dir: Int,
        board: Array<String>
    ): Boolean {
        val nx = x + dx[dir]
        val ny = y + dy[dir]
        return outOfBoundary(nx, ny, board).not() && isBlocked(nx, ny, board).not()
    }

    private fun outOfBoundary(x: Int, y: Int, board: Array<String>): Boolean {
        return x !in board.indices || y !in board[x].indices
    }

    private fun isBlocked(x: Int, y: Int, board: Array<String>): Boolean {
        return board[x][y] == 'D'
    }

    fun solution(board: Array<String>): Int {
        findStartPos(board)
        val q = LinkedList<Node>() as Queue<Node>
        val visited = Array(board.size) { BooleanArray(board[it].length) }
        val (sx, sy) = startPos
        q += Node(sx, sy, 0)
        visited[sx][sy] = true
        while (q.isNotEmpty()) {
            val (x, y, pathCnt) = q.poll()
            if (board[x][y] == 'G') {
                return pathCnt
            }
            for (dir in 0 until 4) {
                var cx = x
                var cy = y
                while (canMoveNext(cx, cy, dir, board)) {
                    cx += dx[dir]
                    cy += dy[dir]
                }
                if (visited[cx][cy]) continue
                visited[cx][cy] = true
                q += Node(cx, cy, pathCnt + 1)
            }
        }
        return -1
    }
}

fun main() {
    val board = arrayOf("...D..R", ".D.G...", "....D.D", "D....D.", "..D....")
    val answer = RicochetRobot().solution(board)
    print(answer)
}