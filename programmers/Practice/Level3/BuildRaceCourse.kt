package programmers.Practice.Level3

import java.util.LinkedList
import java.util.Queue

class BuildRaceCourse {
    private val d = listOf(
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, 1),
        Pair(0, -1)
    )

    private fun outOfBoundary(x: Int, y: Int, size: Int): Boolean {
        return x !in 0 until size || y !in 0 until size
    }

    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        val cost = Array(n) { Array(n) { IntArray(2) { 4000 } } }
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        q += 0 to 0
        cost[0][0][0] = 0
        cost[0][0][1] = 0
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for (i in d.indices) {
                val nx = x + d[i].first
                val ny = y + d[i].second
                if (outOfBoundary(nx, ny, n) || board[nx][ny] == 1) continue
                var nxtDir: Int
                var nxtCost: Int
                if (i < 2) {
                    nxtDir = 1
                    nxtCost = minOf(cost[x][y][0] + 6, cost[x][y][1] + 1)
                } else {
                    nxtDir = 0
                    nxtCost = minOf(cost[x][y][0] + 1, cost[x][y][1] + 6)
                }
                if (nxtCost < cost[nx][ny][nxtDir]) {
                    cost[nx][ny][nxtDir] = nxtCost
                    q += nx to ny
                }
            }
        }
        return minOf(cost[n - 1][n - 1][0], cost[n - 1][n - 1][1]) * 100
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 1, 0, 0, 0, 1),
        intArrayOf(0, 0, 1, 0, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 0, 0, 1, 0, 0),
        intArrayOf(1, 0, 0, 0, 0, 0, 0, 0)
    )
    val answer = BuildRaceCourse().solution(board)
    print(answer)
}