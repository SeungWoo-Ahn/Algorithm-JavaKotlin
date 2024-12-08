package boj.dp

import java.util.StringTokenizer

class `1520` {
    private var map: Array<IntArray> = arrayOf()
    private var dp: Array<IntArray> = arrayOf()
    private var visited: Array<BooleanArray> = arrayOf()
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun dfs(x: Int, y: Int): Int {
        if (x == map.size - 1 && y == map[0].size - 1) {
            return 1
        }
        if (visited[x][y]) {
            return dp[x][y]
        }
        visited[x][y] = true
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (outOfBoundary(nx, ny)) continue
            if (map[x][y] > map[nx][ny]) {
                dp[x][y] += dfs(nx, ny)
            }
        }
        return dp[x][y]
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[0].indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(m) { st.nextToken().toInt() }
        }
        dp = Array(n) { IntArray(m) }
        visited = Array(n) { BooleanArray(m) }
        val result = dfs(0, 0)
        print(result)
    }
}

fun main() {
    `1520`().solution()
}