package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `16933` {
    private enum class Time {
        Day, Night
    }

    private data class Node(
        val x: Int,
        val y: Int,
        val cost: Int,
        val left: Int,
        val time: Time
    )

    private var n = 0
    private var m = 0
    private var k = 0
    private lateinit var map: Array<String>
    private lateinit var visited: Array<Array<BooleanArray>>

    private fun input() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        k = st.nextToken().toInt()
        map = Array(n) { readLine() }
        visited = Array(n) { Array(m) { BooleanArray(k + 1) } }
    }

    private fun bfs(): Int {
        val q = LinkedList<Node>() as Queue<Node>
        val dx = listOf(1, -1, 0, 0)
        val dy = listOf(0, 0, 1, -1)
        q += Node(0, 0, 1, k, Time.Day)
        for (i in 0..k) {
            visited[0][0][i] = true
        }
        while (q.isNotEmpty()) {
            val (x, y, cost, left, time) = q.poll()
            if (x == n - 1 && y == m - 1) {
                return cost
            }
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny)) continue
                when (map[nx][ny]) {
                    '0' -> if (visited[nx][ny][left].not()) {
                        visited[nx][ny][left] = true
                        q += Node(nx, ny, cost + 1, left, time.next())
                    }
                    '1' -> when (time) {
                        Time.Day -> if (left > 0 && visited[nx][ny][left - 1].not()) {
                            visited[nx][ny][left - 1] = true
                            q += Node(nx, ny, cost + 1, left - 1, time.next())
                        }
                        Time.Night -> if (left > 0) {
                            q += Node(x, y, cost + 1, left, time.next())
                        }
                    }
                }
            }
        }
        return -1
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun Time.next(): Time {
        return when (this) {
            Time.Day -> Time.Night
            Time.Night -> Time.Day
        }
    }

    fun solution() {
        input()
        print(bfs())
    }
}

fun main() {
    `16933`().solution()
}