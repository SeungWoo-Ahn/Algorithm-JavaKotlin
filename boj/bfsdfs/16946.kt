package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `16946` {
    private var map: Array<IntArray> = arrayOf()
    private var visited: Array<BooleanArray> = arrayOf()
    private val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
    private val wallQ = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
    private val d = listOf(
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, 1),
        Pair(0, -1),
    )

    private fun bfs(sx: Int, sy: Int) {
        q += sx to sy
        visited[sx][sy] = true
        var cnt = 0
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            cnt++
            for ((dx, dy) in d) {
                val nx = x + dx
                val ny = y + dy
                if (outOfBoundary(nx, ny) || visited[nx][ny]) continue
                visited[nx][ny] = true
                if (map[nx][ny] == 0) q += nx to ny
                else wallQ += nx to ny
            }
        }
        while (wallQ.isNotEmpty()) {
            val (x, y) = wallQ.poll()
            map[x][y] += cnt
            visited[x][y] = false
        }
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun printAnswer(n: Int, m: Int) {
        val sb = StringBuilder()
        for (x in 0 until n) {
            for (y in 0 until m) {
                sb.append(map[x][y] % 10)
            }
            sb.appendLine()
        }
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        map = Array(n) {
            val line = readLine()
            IntArray(m) { line[it] - '0' }
        }
        visited = Array(n) { BooleanArray(m) }
        for (x in 0 until n) {
            for (y in 0 until m) {
                if (map[x][y] == 0 && !visited[x][y]) {
                    bfs(x, y)
                }
            }
        }
        printAnswer(n, m)
    }
}

fun main() {
    `16946`().solution()
}