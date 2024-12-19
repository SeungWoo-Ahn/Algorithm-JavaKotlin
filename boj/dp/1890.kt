package boj.dp

import java.util.StringTokenizer

class `1890` {
    private fun outOfBoundary(n: Int, x: Int, y: Int): Boolean {
        return x !in 0 until n || y !in 0 until n
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        val board = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        val dp = Array(n) { LongArray(n) }
        val dx = intArrayOf(1, 0)
        val dy = intArrayOf(0, 1)
        dp[0][0] = 1L
        for (x in board.indices) {
            for (y in board.indices) {
                if (board[x][y] == 0) continue
                for (i in 0 until 2) {
                    val nx = x + dx[i] * board[x][y]
                    val ny = y + dy[i] * board[x][y]
                    if (outOfBoundary(n, nx, ny)) continue
                    dp[nx][ny] += dp[x][y]
                }
            }
        }
        print(dp[n - 1][n - 1])
    }
}

fun main() {
    `1890`().solution()
}