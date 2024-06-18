package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `6593` {
    private data class Square(val x: Int, val y: Int, val z: Int)

    private var building: Array<Array<IntArray>> = arrayOf()
    private var visited: Array<Array<BooleanArray>> = arrayOf()
    private val d = listOf(
        Square(1, 0, 0),
        Square(-1, 0, 0),
        Square(0, 1, 0),
        Square(0, -1, 0),
        Square(0, 0, 1),
        Square(0, 0, -1),
    )

    private fun testCase(st: Square, en: Square): String {
        bfs(st, en)
        val minTime = building[en.x][en.y][en.z]
        return if (minTime == 0) "Trapped!"
        else "Escaped in $minTime minute(s)."
    }

    private fun bfs(st: Square, en: Square) {
        val q = LinkedList<Square>() as Queue<Square>
        q.offer(st)
        visited[st.x][st.y][st.z] = true
        while (q.isNotEmpty()) {
            val (x, y, z) = q.poll()
            if (x == en.x && y == en.y && z == en.z) break
            for ((dx, dy, dz) in d) {
                val nx = x + dx
                val ny = y + dy
                val nz = z + dz
                if (outOfBoundary(nx, ny, nz)) continue
                if (visited[nx][ny][nz] || building[nx][ny][nz] < 0) continue
                visited[nx][ny][nz] = true
                building[nx][ny][nz] = building[x][y][z] + 1
                q.offer(Square(nx, ny, nz))
            }
        }
    }

    private fun outOfBoundary(x: Int, y: Int, z: Int): Boolean {
        return x !in building.indices || y !in building[0].indices || z !in building[0][0].indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        while (true) {
            val (l, r, c) = readLine().split(" ").map { it.toInt() }
            if (l == 0 && r == 0 && c == 0) break
            building = Array(r) { Array(c) { IntArray(l) } }
            visited = Array(r) { Array(c) { BooleanArray(l) } }
            var st = Square(0, 0, 0)
            var en = Square(0, 0, 0)
            repeat(l) { z ->
                repeat(r) { x ->
                    val line = readLine()
                    repeat(c) { y ->
                        val num = when (val char = line[y]) {
                            '#' -> -1
                            else -> {
                                if (char == 'S') st = Square(x, y, z)
                                if (char == 'E') en = Square(x, y, z)
                                0
                            }
                        }
                        building[x][y][z] = num
                    }
                }
                readLine()
            }
            sb.appendLine(testCase(st, en))
        }
        print(sb)
    }
}

fun main() {
    `6593`().solution()
}