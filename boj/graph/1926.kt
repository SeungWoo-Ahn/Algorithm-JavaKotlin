package boj.graph

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `1926` {
    private var cnt = 0
    private var maxSize = 0
    private val dx = listOf(1, -1, 0, 0)
    private val dy = listOf(0, 0, 1, -1)

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val visited = Array(n) { BooleanArray(m) }
        val paint = Array(n) { IntArray(m) }.apply {
            repeat(n) { x ->
                val st = StringTokenizer(readLine(), " ")
                repeat(m) { y ->
                    this[x][y] = st.nextToken().toInt()
                }
            }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (!visited[i][j] && paint[i][j] == 1)
                    bfs(i, j, paint, visited)
            }
        }
        println(cnt)
        print(maxSize)
    }

    private fun bfs(x: Int, y: Int, paint: Array<IntArray>, visited: Array<BooleanArray>) {
        cnt++
        visited[x][y] = true
        val q: Queue<Pair<Int, Int>> = LinkedList()
        q.offer(Pair(x, y))
        var size = 0

        while (q.isNotEmpty()) {
            val cur = q.poll()
            size++
            for (i in 0 until 4) {
                val nx = cur.first + dx[i]
                val ny = cur.second + dy[i]
                if (nx !in paint.indices || ny !in paint[0].indices || visited[nx][ny]) continue
                if (paint[nx][ny] == 1) {
                    q.offer(Pair(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }

        if (size > maxSize) maxSize = size
    }
}

fun main() {
    `1926`().solution()
}

