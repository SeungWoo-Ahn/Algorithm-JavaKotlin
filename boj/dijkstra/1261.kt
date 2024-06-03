package boj.dijkstra

import java.util.PriorityQueue

class `1261` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (m, n) = readLine().split(" ").map { it.toInt() }
        val miro = Array(n + 1) { IntArray(m + 1) }
        for (i in 1 .. n) {
            val row = readLine()
            for (j in 1 .. m) {
                miro[i][j] = row[j - 1] - '0'
            }
        }
        val d = Array(n + 1) { IntArray(m + 1) { INF } }
        val dir = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        d[1][1] = 0
        pq.add(Triple(0, 1, 1))
        while (pq.isNotEmpty()) {
            val (cost, x, y) = pq.poll()
            if (d[x][y] != cost) continue
            if (x == n && y == m) break
            for ((dx, dy) in dir) {
                val nx = x + dx
                val ny = y + dy
                if (nx !in 1 .. n || ny !in 1 .. m) continue
                if (d[nx][ny] <= cost + miro[nx][ny]) continue
                d[nx][ny] = cost + miro[nx][ny]
                pq.add(Triple(d[nx][ny], nx, ny))
            }
        }
        print(d[n][m])
    }

    companion object {
        private const val INF = 100_000
    }
}

fun main() {
    `1261`().solution()
}