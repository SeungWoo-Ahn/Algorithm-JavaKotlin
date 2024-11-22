package programmers.Practice.Level3

import java.util.LinkedList
import java.util.Queue

class MoveBlock {
    private data class Pos(val x: Int, val y: Int)
    private data class Robot(val pos: Pos, val mode: Mode, val time: Int)

    private sealed interface Mode {
        fun index(): Int
        fun getTailPos(pos: Pos): Pos
        fun getRotateDir(): IntArray
        fun getOppositeMode(): Mode

        data object Horizontal : Mode {
            override fun index(): Int = 0
            override fun getTailPos(pos: Pos): Pos = pos.copy(y = pos.y - 1)
            override fun getRotateDir(): IntArray = intArrayOf(2, 3)
            override fun getOppositeMode(): Mode = Vertical
        }

        data object Vertical : Mode {
            override fun index(): Int = 1
            override fun getTailPos(pos: Pos): Pos = pos.copy(x = pos.x - 1)
            override fun getRotateDir(): IntArray = intArrayOf(0, 1)
            override fun getOppositeMode(): Mode = Horizontal
        }
    }

    private val d = listOf(
        Pos(0, -1),
        Pos(0, 1),
        Pos(-1, 0),
        Pos(1, 0)
    )

    private fun isArrived(n: Int, pos: Pos): Boolean {
        return pos.x == n - 1 && pos.y == n - 1
    }

    private fun outOfBoundary(n: Int, x: Int, y: Int): Boolean {
        return x !in 0 until n || y !in 0 until n
    }

    private fun cantMove(n: Int, x: Int, y: Int, board: Array<IntArray>): Boolean {
        return outOfBoundary(n, x, y) || board[x][y] == WALL
    }

    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        val q = LinkedList<Robot>() as Queue<Robot>
        val visited = Array(n) { Array(n) { BooleanArray(2) } }
        q += Robot(
            pos = Pos(0, 1),
            mode = Mode.Horizontal,
            time = 0
        )
        visited[0][1][Mode.Horizontal.index()] = true
        while (q.isNotEmpty()) {
            val (headPos, mode, time) = q.poll()
            if (isArrived(n, headPos)) {
                return time
            }
            val tailPos = mode.getTailPos(headPos)
            for ((dx, dy) in d) { // 이동
                val nhx = headPos.x + dx
                val nhy = headPos.y + dy
                val ntx = tailPos.x + dx
                val nty = tailPos.y + dy
                if (cantMove(n, nhx, nhy, board) || cantMove(n, ntx, nty, board)) continue
                if (visited[nhx][nhy][mode.index()]) continue
                visited[nhx][nhy][mode.index()] = true
                q += Robot(
                    pos = Pos(nhx, nhy),
                    mode = mode,
                    time = time + 1
                )
            }
            for (i in mode.getRotateDir()) { // head를 축으로 회전
                val rx = headPos.x + d[i].x
                val ry = headPos.y + d[i].y
                if (cantMove(n, rx, ry, board)) continue
                val px = headPos.x + d[mode.index() * 2].x + d[i].x
                val py = headPos.y + d[mode.index() * 2].y + d[i].y
                if (cantMove(n, px, py, board)) continue
                val nhx = if (rx > headPos.x) rx else headPos.x
                val nhy = if (ry > headPos.y) ry else headPos.y
                if (visited[nhx][nhy][mode.getOppositeMode().index()]) continue
                visited[nhx][nhy][mode.getOppositeMode().index()] = true
                q += Robot(
                    pos = Pos(nhx, nhy),
                    mode = mode.getOppositeMode(),
                    time = time + 1
                )
            }
            for (i in mode.getRotateDir()) { // tail을 축으로 회전
                val rx = tailPos.x + d[i].x
                val ry = tailPos.y + d[i].y
                if (cantMove(n, rx, ry, board)) continue
                val px = tailPos.x + d[mode.index() * 2 + 1].x + d[i].x
                val py = tailPos.y + d[mode.index() * 2 + 1].y + d[i].y
                if (cantMove(n, px, py, board)) continue
                val nhx = if (rx > tailPos.x) rx else tailPos.x
                val nhy = if (ry > tailPos.y) ry else tailPos.y
                if (visited[nhx][nhy][mode.getOppositeMode().index()]) continue
                visited[nhx][nhy][mode.getOppositeMode().index()] = true
                q += Robot(
                    pos = Pos(nhx, nhy),
                    mode = mode.getOppositeMode(),
                    time = time + 1
                )
            }
        }
        return 0
    }

    companion object {
        private const val WALL = 1
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 1, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0)
    )
    val answer = MoveBlock().solution(board)
    print(answer)
}