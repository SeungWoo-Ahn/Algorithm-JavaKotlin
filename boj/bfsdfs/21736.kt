package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `21736` {
    private var map: Array<CharArray> = arrayOf()
    private var visited: Array<BooleanArray> = arrayOf()
    private val d = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

    private fun findStartPos(): Pair<Int, Int> {
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] == 'I')
                    return x to y
            }
        }
        return 0 to 0
    }

    private fun bfs(startPos: Pair<Int, Int>): String {
        var friendCnt = 0
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        q += startPos
        visited[startPos.first][startPos.second] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for ((dx, dy) in d) {
                val nx = x + dx
                val ny = y + dy
                if (!canMove(nx, ny)) continue
                if (map[nx][ny] == 'P') friendCnt++
                visited[nx][ny] = true
                q += nx to ny
            }
        }
        return if (friendCnt > 0) friendCnt.toString()
        else "TT"
    }

    private fun canMove(x: Int, y: Int): Boolean {
        return !outOfBoundary(x, y) && !visited[x][y] && map[x][y] != 'X'
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[0].indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        map = Array(n) { readLine().toCharArray() }
        visited = Array(n) { BooleanArray(m) }
        val startPos = findStartPos()
        val result = bfs(startPos)
        print(result)
    }
}

fun main() {
    `21736`().solution()
}