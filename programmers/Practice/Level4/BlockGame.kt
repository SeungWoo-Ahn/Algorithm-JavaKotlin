package programmers.Practice.Level4

import java.util.LinkedList
import java.util.Queue

class BlockGame {
    private data class Pos(val x: Int, val y: Int)

    private data class Box(
        val num: Int,
        val xRange: IntRange,
        val yRange: IntRange,
    ) {
        val otherPosList = mutableListOf<Pos>()

        fun setOtherPosList(board: Array<IntArray>) {
            for (x in xRange) {
                for (y in yRange) {
                    if (board[x][y] != num) {
                        otherPosList += Pos(x, y)
                    }
                }
            }
        }

        fun getOtherNumSet(board: Array<IntArray>): Set<Int> {
            val otherNumSet = mutableSetOf<Int>()
            for ((x, y) in otherPosList) {
                if (board[x][y] != num && board[x][y] != 0) {
                    otherNumSet += board[x][y]
                }
            }
            return otherNumSet
        }

        fun removeFromBoard(board: Array<IntArray>) {
            for (x in xRange) {
                for (y in yRange) {
                    if (board[x][y] == num) {
                        board[x][y] = 0
                    }
                }
            }
        }
    }

    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)
    private val q = LinkedList<Pos>() as Queue<Pos>
    private val boxList = mutableListOf<Box>()
    private lateinit var boxChecked: BooleanArray

    private fun bfs(sx: Int, sy: Int, board: Array<IntArray>, visited: Array<BooleanArray>): Box {
        val num = board[sx][sy]
        var minX = board.size
        var maxX = 0
        var minY = board.size
        var maxY = 0
        q += Pos(sx, sy)
        visited[sx][sy] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            minX = minOf(minX, x)
            maxX = maxOf(maxX, x)
            minY = minOf(minY, y)
            maxY = maxOf(maxY, y)
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny, board) || visited[nx][ny] || board[nx][ny] != num) continue
                q += Pos(nx, ny)
                visited[nx][ny] = true
            }
        }
        return Box(num, minX..maxX, minY..maxY).apply { setOtherPosList(board) }
    }

    private fun outOfBoundary(x: Int, y: Int, board: Array<IntArray>): Boolean {
        return x !in board.indices || y !in board[x].indices
    }

    private fun cntRemovableBox(idx: Int, board: Array<IntArray>): Int {
        if (boxChecked[idx]) return 0
        boxChecked[idx] = true
        val box = boxList[idx]
        val otherNumSet = box.getOtherNumSet(board)
        var cnt = 0
        for (otherNum in otherNumSet) {
            val nxt = boxList.indexOfFirst { it.num == otherNum }
            cnt += cntRemovableBox(nxt, board)
        }
        if (checkRemovable(box, board)) {
            box.removeFromBoard(board)
            cnt++
        }
        return cnt
    }

    private fun checkRemovable(box: Box, board: Array<IntArray>): Boolean {
        for ((x, y) in box.otherPosList) {
            for (i in x downTo 0) {
                if (board[i][y] != 0) {
                    return false
                }
            }
        }
        return true
    }

    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        val visited = Array(n) { BooleanArray(n) }
        for (x in board.indices) {
            for (y in board[x].indices) {
                if (board[x][y] == 0 || visited[x][y]) continue
                val box = bfs(x, y, board, visited)
                boxList += box
            }
        }
        var removableCnt = 0
        boxChecked = BooleanArray(boxList.size)
        for (i in boxList.indices) {
            removableCnt += cntRemovableBox(i, board)
        }
        return removableCnt
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 4, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 4, 4, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 3, 0, 4, 0, 0, 0),
        intArrayOf(0, 0, 0, 2, 3, 0, 0, 0, 5, 5),
        intArrayOf(1, 2, 2, 2, 3, 3, 0, 0, 0, 5),
        intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 0, 5)
    )
    val answer = BlockGame().solution(board)
    print(answer)
}