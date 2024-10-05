package programmers.Practice.Level2

import java.util.LinkedList
import java.util.Queue

class EscapeMaze {
    private data class Node(val x: Int, val y: Int, val time: Int)

    private val startPos = IntArray(2)
    private val leverPos = IntArray(2)
    private val exitPos = IntArray(2)
    private var board: Array<BooleanArray> = arrayOf()

    private fun makeBoard(maps: Array<String>) {
        board = Array(maps.size) { BooleanArray(maps[0].length) }
        for (x in board.indices) {
            for (y in board[x].indices) {
                if (maps[x][y] == 'X') continue
                when (maps[x][y]) {
                    'S' -> {
                        startPos[0] = x
                        startPos[1] = y
                    }
                    'L' -> {
                        leverPos[0] = x
                        leverPos[1] = y
                    }
                    'E' -> {
                        exitPos[0] = x
                        exitPos[1] = y
                    }
                }
                board[x][y] = true
            }
        }
    }

    private fun findShortestWay(startPos: IntArray, endPos: IntArray): Int {
        val q = LinkedList<Node>() as Queue<Node>
        val visited = Array(board.size) { BooleanArray(board[0].size) }
        val dx = intArrayOf(1, -1, 0, 0)
        val dy = intArrayOf(0, 0, 1, -1)
        val (sx, sy) = startPos
        val (ex, ey) = endPos
        q += Node(sx, sy, 0)
        visited[sx][sy] = true
        while (q.isNotEmpty()) {
            val (x, y, time) = q.poll()
            if (x == ex && y == ey) {
                return time
            }
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny) || visited[nx][ny] || board[nx][ny].not()) continue
                visited[nx][ny] = true
                q += Node(nx, ny, time + 1)
            }
        }
        return -1
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in board.indices || y !in board[x].indices
    }

    fun solution(maps: Array<String>): Int {
        makeBoard(maps)
        val sToL = findShortestWay(startPos, leverPos)
        if (sToL < 0) {
            return -1
        }
        val lToE = findShortestWay(leverPos, exitPos)
        if (lToE < 0) {
            return -1
        }
        return sToL + lToE
    }
}

fun main() {
    val maps = arrayOf("SOOOL","XXXXO","OOOOO","OXXXX","OOOOE")
    val answer = EscapeMaze().solution(maps)
    print(answer)
}