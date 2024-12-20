package boj.dp

import java.util.StringTokenizer

class `1937` {
    private lateinit var forest: Array<IntArray>
    private lateinit var dp: Array<IntArray>
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun input() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        forest = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        dp = Array(n) { IntArray(n) }
    }

    private fun dfs(x: Int, y: Int): Int {
        var max = 0
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (outOfBoundary(nx, ny) || forest[x][y] >= forest[nx][ny]) continue
            val result = if (dp[nx][ny] > 0) dp[nx][ny] else dfs(nx, ny)
            max = maxOf(max, result)
        }
        dp[x][y] = max + 1
        return dp[x][y]
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in forest.indices || y !in forest.indices
    }

    fun solution() {
        input()
        var result = 0
        for (x in forest.indices) {
            for (y in forest.indices) {
                if (dp[x][y] > 0) continue
                dfs(x, y)
                result = maxOf(result, dp[x][y])
            }
        }
        print(result)
    }
}

fun main() {
    `1937`().solution()
}