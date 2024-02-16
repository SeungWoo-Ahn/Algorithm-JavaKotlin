package boj.simulation

import java.util.StringTokenizer

class `14503` {
    data class Node(val x: Int, val y: Int)

    private var map: Array<IntArray> = arrayOf()
    private var visited: Array<BooleanArray> = arrayOf()
    private val d = listOf(Node(-1, 0), Node(0, 1), Node(1, 0), Node(0, -1))
    private var answer = 0

    private fun work(x: Int, y: Int, dir: Int) {
        var curX = x
        var curY = y
        var curDir = dir
        while (true) {
            if (isNotClean(curX, curY)) {
                visited[curX][curY] = true
                answer++
            }
            if (findAdj(curX, curY)) {
                curDir = (curDir + 3) % 4
                val way = d[curDir]
                val nx = curX + way.x
                val ny = curY + way.y
                if (isNotClean(nx, ny)) {
                    curX = nx
                    curY = ny
                }
            } else {
                val way = d[(curDir + 2) % 4]
                val nx = curX + way.x
                val ny = curY + way.y
                if (map[nx][ny] == 1) break
                curX = nx
                curY = ny
            }
        }
    }

    private fun isNotClean(x: Int, y: Int): Boolean {
        return !visited[x][y] && map[x][y] == 0
    }

    private fun findAdj(x: Int, y: Int): Boolean {
        for (i in 0 until 4) {
            val nx = x + d[i].x
            val ny = y + d[i].y
            if (isNotClean(nx, ny)) return true
        }
        return false
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        val (r, c, d) = readLine().split(" ").map { it.toInt() }
        map = Array(N) {
            val st = StringTokenizer(readLine())
            IntArray(M) { st.nextToken().toInt() }
        }
        visited = Array(N) { BooleanArray(M) }
        work(r, c, d)
        println(answer)
    }
}

fun main() {
    `14503`().solution()
}