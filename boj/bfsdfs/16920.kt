package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `16920` {
    private data class Node(val x: Int, val y: Int)

    private var n = 0
    private var m = 0
    private var p = 0
    private var emptyCnt = 0
    private lateinit var board: Array<IntArray>
    private lateinit var s: IntArray
    private lateinit var cntByPlayer: IntArray
    private lateinit var queueByPlayer: Array<Queue<Node>>
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        p = st.nextToken().toInt()
        st = StringTokenizer(readLine(), " ")
        s = IntArray(p) { st.nextToken().toInt() }
        board = Array(n) {
            val col = readLine()
            IntArray(m) { i ->
                when (col[i]) {
                    '#' -> WALL
                    '.' -> EMPTY
                    else -> col[i] - '1'
                }
            }
        }
        cntByPlayer = IntArray(p)
        queueByPlayer = Array(p) { LinkedList() }
    }

    private fun init() {
        cntAvailableEmptySpaces()
        checkGroups()
    }

    private fun cntAvailableEmptySpaces() {
        val visited = Array(n) { BooleanArray(m) }
        for (x in board.indices) {
            for (y in board[x].indices) {
                if (board[x][y] in 0..8 && visited[x][y].not()) {
                    cntAvailableEmptySpace(x, y, visited)
                }
            }
        }
    }

    private fun cntAvailableEmptySpace(x: Int, y: Int, visited: Array<BooleanArray>) {
        val q = LinkedList<Node>() as Queue<Node>
        q += Node(x, y)
        visited[x][y] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                if (outOfBoundary(nx, ny) || visited[nx][ny] || board[nx][ny] == WALL) continue
                if (board[nx][ny] == EMPTY) emptyCnt++
                visited[nx][ny] = true
                q += Node(nx, ny)
            }
        }
    }

    private fun checkGroups() {
        val visited = Array(n) { BooleanArray(m) }
        for (x in board.indices) {
            for (y in board[x].indices) {
                if (board[x][y] in 0..8 && visited[x][y].not()) {
                    checkGroup(x, y, visited)
                }
            }
        }
    }

    private fun checkGroup(x: Int, y: Int, visited: Array<BooleanArray>) {
        val q = LinkedList<Node>() as Queue<Node>
        val target = board[x][y]
        var groupCnt = 0
        q += Node(x, y)
        visited[x][y] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            groupCnt++
            if (checkAdjEmpty(cur)) {
                queueByPlayer[target] += cur
            }
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                if (outOfBoundary(nx, ny) || visited[nx][ny] || board[nx][ny] != target) continue
                visited[nx][ny] = true
                q += Node(nx, ny)
            }
        }
        cntByPlayer[target] += groupCnt
    }

    private fun checkAdjEmpty(node: Node): Boolean {
        for (i in 0 until 4) {
            val nx = node.x + dx[i]
            val ny = node.y + dy[i]
            if (outOfBoundary(nx, ny)) continue
            if (board[nx][ny] == EMPTY) {
                return true
            }
        }
        return false
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in board.indices || y !in board[x].indices
    }

    private fun spread(turn: Int) {
        var depth = 0
        var spreadCnt = 0
        while (queueByPlayer[turn].isNotEmpty() && depth < s[turn]) {
           val size = queueByPlayer[turn].size
           repeat(size) {
               val (x, y) = queueByPlayer[turn].poll()
               for (i in 0 until 4) {
                   val nx = x + dx[i]
                   val ny = y + dy[i]
                   if (outOfBoundary(nx, ny) || board[nx][ny] != EMPTY) continue
                   spreadCnt++
                   board[nx][ny] = turn
                   queueByPlayer[turn] += Node(nx, ny)
               }
           }
           depth++
        }
        emptyCnt -= spreadCnt
        cntByPlayer[turn] += spreadCnt
    }

    private fun getNextTurn(turn: Int): Int {
        return (turn + 1) % p
    }

    fun solution() {
        input()
        init()
        var turn = 0
        while (emptyCnt > 0) {
            spread(turn)
            turn = getNextTurn(turn)
        }
        print(cntByPlayer.joinToString(" "))
    }

    companion object {
        private const val EMPTY = -1
        private const val WALL = -2
    }
}

fun main() {
    `16920`().solution()
}