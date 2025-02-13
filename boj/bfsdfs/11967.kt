package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `11967` {
    private data class Pos(val a: Int, val b: Int)

    private lateinit var lightOn: Array<BooleanArray>
    private lateinit var visited: Array<BooleanArray>
    private lateinit var switches: Array<Array<MutableList<Pos>>>
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun isLinked(x: Int, y: Int): Boolean {
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (outOfBoundary(nx, ny)) continue
            if (visited[nx][ny]) {
                return true
            }
        }
        return false
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in lightOn.indices || y !in lightOn[x].indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        lightOn = Array(n) { BooleanArray(n) }
        visited = Array(n) { BooleanArray(n) }
        switches = Array(n) { Array(n) { mutableListOf() } }
        repeat(m) {
            val (x, y, a, b) = readLine().splitToInt(offset = 1)
            switches[x][y] += Pos(a, b)
        }
        val q = LinkedList<Pos>() as Queue<Pos>
        q += Pos(0, 0)
        lightOn[0][0] = true
        visited[0][0] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for ((a, b) in switches[x][y]) {
                if (visited[a][b]) continue
                lightOn[a][b] = true
                if (isLinked(a, b)) {
                    visited[a][b] = true
                    q += Pos(a, b)
                }
            }
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny) || visited[nx][ny] || lightOn[nx][ny].not()) continue
                visited[nx][ny] = true
                q += Pos(nx, ny)
            }
        }
        val result = lightOn.sumOf { b -> b.count { it } }
        print(result)
    }

    private fun String.splitToInt(offset: Int = 0) = split(" ").map { it.toInt() - offset }
}

fun main() {
    `11967`().solution()
}