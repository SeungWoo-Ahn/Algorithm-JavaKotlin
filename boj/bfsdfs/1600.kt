package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `1600` {
    private data class Monkey(val x: Int, val y: Int, val horseMoving: Int, val cost: Int)

    private var map: Array<IntArray> = arrayOf()
    private var visited: Array<Array<BooleanArray>> = arrayOf()
    private val dx = listOf(1, -1, 0, 0)
    private val dy = listOf(0, 0, 1, -1)
    private val hx = listOf(2, 2, -2, -2, 1, 1, -1, -1)
    private val hy = listOf(1, -1, 1, -1, 2, -2, 2, -2)

    private fun bfs(k: Int, w: Int, h: Int): Int {
        val q = LinkedList<Monkey>() as Queue<Monkey>
        q += Monkey(0, 0, k, 0)
        visited[0][0][k] = true
        while (q.isNotEmpty()) {
            val (x, y, hm, cost) = q.poll()
            if (x == h - 1 && y == w - 1) return cost
            if (hm > 0) {
                for (i in hx.indices) {
                    val nx = x + hx[i]
                    val ny = y + hy[i]
                    if (!check(nx, ny, hm - 1)) continue
                    visited[nx][ny][hm - 1] = true
                    q += Monkey(nx, ny, hm - 1, cost + 1)
                }
            }
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (!check(nx, ny, hm)) continue
                visited[nx][ny][hm] = true
                q += Monkey(nx, ny, hm, cost + 1)
            }
        }
        return -1
    }

    private fun check(x: Int, y: Int, hm: Int): Boolean {
        if (outOfBoundary(x, y) || visited[x][y][hm]) return false
        if (map[x][y] == 1) return false
        return true
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[0].indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val k = readLine().toInt()
        val (w, h) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        map = Array(h) {
            st = StringTokenizer(readLine())
            IntArray(w) { st.nextToken().toInt() }
        }
        visited = Array(h) { Array(w) { BooleanArray(32) } }
        print(bfs(k, w, h))
    }
}
fun main() {
    `1600`().solution()
}