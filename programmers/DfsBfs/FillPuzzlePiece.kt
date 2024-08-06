package programmers.DfsBfs

import java.util.LinkedList
import java.util.Queue

class FillPuzzlePiece {
    private val pieces = Array(7) { mutableListOf<Array<IntArray>>() }
    private val d = listOf(
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, 1),
        Pair(0, -1)
    )
    private var filledCnt = 0

    private fun insertPieces(table: Array<IntArray>) {
        val visited = Array(table.size) { BooleanArray(table.size) }
        for (i in table.indices) {
            for (j in table.indices) {
                if (visited[i][j]) continue
                if (table[i][j] == 1) {
                    val (piece, cnt) = takePart(i, j, 1, visited, table)
                    pieces[cnt] += piece
                }
            }
        }
    }

    private fun takePart(
        sx: Int,
        sy: Int,
        target: Int,
        visited: Array<BooleanArray>,
        map: Array<IntArray>
    ): Pair<Array<IntArray>, Int> {
        val pos = mutableListOf<Pair<Int, Int>>()
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        q += sx to sy
        visited[sx][sy] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            pos += cur
            for ((dx, dy) in d) {
                val nx = cur.first + dx
                val ny = cur.second + dy
                if (outOfBoundary(nx, ny, map.size) || visited[nx][ny]) continue
                if (map[nx][ny] == target) {
                    visited[nx][ny] = true
                    q += nx to ny
                }
            }
        }
        var minX = map.size
        var minY = map.size
        var maxX = 0
        var maxY = 0
        for ((x, y) in pos) {
            minX = minOf(minX, x)
            minY = minOf(minY, y)
            maxX = maxOf(maxX, x)
            maxY = maxOf(maxY, y)
        }
        val h = maxX - minX + 1
        val w = maxY - minY + 1
        val part = Array(h) { IntArray(w) }
        for (i in 0 until h) {
            for (j in 0 until w) {
                part[i][j] = map[i + minX][j + minY]
            }
        }
        return part to pos.size
    }

    private fun outOfBoundary(x: Int, y: Int, size: Int): Boolean {
        return x !in 0 until size || y !in 0 until size
    }

    private fun fillBoard(gameBoard: Array<IntArray>) {
        val visited = Array(gameBoard.size) { BooleanArray(gameBoard.size) }
        for (i in gameBoard.indices) {
            for (j in gameBoard.indices) {
                if (visited[i][j]) continue
                if (gameBoard[i][j] == 0) {
                    val (part, cnt) = takePart(i, j, 0, visited, gameBoard)
                    if (canPutPiece(part, cnt)) {
                        filledCnt += cnt
                    }
                }
            }
        }
    }

    private fun canPutPiece(part: Array<IntArray>, cnt: Int): Boolean {
        for (i in 0 until pieces[cnt].size) {
            var piece = pieces[cnt][i]
            for (r in 1..4) {
                if (isSame(piece, part)) {
                    pieces[cnt].removeAt(i)
                    return true
                }
                if (r < 4) {
                    piece = rotate(piece)
                }
            }
        }
        return false
    }

    private fun isSame(p1: Array<IntArray>, p2: Array<IntArray>): Boolean {
        if (p1.size != p2.size || p1[0].size != p2[0].size) return false
        for (i in p1.indices) {
            for (j in p1[0].indices) {
                if (p1[i][j] == p2[i][j]) {
                    return false
                }
            }
        }
        return true
    }

    private fun rotate(piece: Array<IntArray>): Array<IntArray> {
        return Array(piece[0].size) { x ->
            IntArray(piece.size) { y ->
                piece[piece.size - y - 1][x]
            }
        }
    }

    fun solution(gameBoard: Array<IntArray>, table: Array<IntArray>): Int {
        insertPieces(table)
        fillBoard(gameBoard)
        return filledCnt
    }
}

fun main() {
    val gameBoard = arrayOf(
        intArrayOf(1, 1, 0, 0, 1, 0),
        intArrayOf(0, 0, 1, 0, 1, 0),
        intArrayOf(0, 1, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 1, 0),
        intArrayOf(0, 1, 1, 1, 0, 0)
    )
    val table = arrayOf(
        intArrayOf(1, 0, 0, 1, 1, 0),
        intArrayOf(1, 0, 1, 0, 1, 0),
        intArrayOf(0, 1, 1, 0, 1, 1),
        intArrayOf(0, 0, 1, 0, 0, 0),
        intArrayOf(1, 1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 0, 0, 0)
    )
    val answer = FillPuzzlePiece().solution(gameBoard, table)
    print(answer)
}