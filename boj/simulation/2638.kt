package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `2638` {
    private var map: Array<IntArray> = arrayOf()
    private val d = listOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    private fun injectAir(n: Int, m: Int) {
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        val visited = Array(n) { BooleanArray(m) }
        q += 0 to 0
        visited[0][0] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            map[x][y] = AIR
            for ((dx, dy) in d) {
                val nx = x + dx
                val ny = y + dy
                if (outOfBoundary(nx, ny) || visited[nx][ny]) continue
                if (map[nx][ny] == CHEESE) continue
                visited[nx][ny] = true
                q += nx to ny
            }
        }
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[0].indices
    }

    private fun meltCheese(n: Int, m: Int): Boolean {
        var cheeseCnt = 0
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        for (x in 1 until n - 1) {
            for (y in 1 until m - 1) {
                if (map[x][y] != CHEESE) continue
                var adjAirCnt = 0
                for ((dx, dy) in d) {
                    val nx = x + dx
                    val ny = y + dy
                    if (map[nx][ny] == AIR) {
                        adjAirCnt++
                    }
                }
                if (adjAirCnt >= 2) {
                    q += x to y
                }
                cheeseCnt++
            }
        }
        if (cheeseCnt == q.size) return false
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            map[x][y] = BLANK
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(m) { st.nextToken().toInt() }
        }
        var time = 0
        while (true) {
            time++
            injectAir(n, m)
            if (!meltCheese(n, m)) break
        }
        print(time)
    }

    companion object {
        private const val AIR = -1
        private const val BLANK = 0
        private const val CHEESE = 1
    }
}

fun main() {
    `2638`().solution()
}