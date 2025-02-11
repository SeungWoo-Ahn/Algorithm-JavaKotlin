package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `14442` {
    data class Node(val x: Int, val y: Int, val left: Int)

    private var n = 0
    private var m = 0
    private var k = 0
    private lateinit var map: Array<String>
    private lateinit var cost: Array<Array<IntArray>>

    private fun input() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        k = st.nextToken().toInt()
        map = Array(n) { readLine() }
        cost = Array(n) { Array(m) { IntArray(k + 1) } }
    }

    private fun bfs(): Int {
        val q = LinkedList<Node>() as Queue<Node>
        val dx = intArrayOf(1, -1, 0, 0)
        val dy = intArrayOf(0, 0, 1, -1)
        q += Node(0, 0, k)
        cost[0][0][k] = 1
        while (q.isNotEmpty()) {
            val (x, y, left) = q.poll()
            if (x == n - 1 && y == m - 1) {
                return cost[x][y][left]
            }
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny)) continue
                when (map[nx][ny]) {
                    '0' -> if (cost[nx][ny][left] == 0) {
                        cost[nx][ny][left] = cost[x][y][left] + 1
                        q += Node(nx, ny, left)
                    }
                    '1' -> if (left > 0 && cost[nx][ny][left - 1] == 0) {
                        cost[nx][ny][left - 1] = cost[x][y][left] + 1
                        q += Node(nx, ny, left - 1)
                    }
                }
            }
        }
        return -1
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    fun solution() {
        input()
        print(bfs())
    }
}

fun main() {
    `14442`().solution()
}