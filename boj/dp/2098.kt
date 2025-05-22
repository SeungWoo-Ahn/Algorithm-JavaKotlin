package boj.dp

import java.util.StringTokenizer

class `2098` {
    private var n = 0
    private lateinit var w: Array<IntArray>
    private lateinit var dp: Array<IntArray>

    private fun input() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()
        var st: StringTokenizer
        w = Array(n) {
            st = StringTokenizer(readLine(), " ")
            IntArray(n) { st.nextToken().toInt() }
        }
        dp = Array(n) { IntArray(1 shl n) { -1 } }
    }

    private fun dfs(node: Int, visited: Int): Int {
        if (visited == (1 shl n) - 1) { // 전체 방문
            dp[node][visited] = if (w[node][0] > 0) w[node][0] else INF
            return dp[node][visited]
        }
        if (dp[node][visited] != -1) { // 이전에 방문
            return dp[node][visited]
        }
        var nxtExist = false
        for (nxt in 1 until n) {
            if ((visited shr nxt) and 1 == 1 || w[node][nxt] == 0) continue
            val nxtCost = dfs(nxt, visited or (1 shl nxt))
            if (nxtCost != INF) {
                if (dp[node][visited] == -1) {
                    dp[node][visited] = nxtCost + w[node][nxt]
                } else {
                    dp[node][visited] = minOf(dp[node][visited], nxtCost + w[node][nxt])
                }
                nxtExist = true
            }
        }
        if (nxtExist.not()) {
            dp[node][visited] = INF
        }
        return dp[node][visited]
    }

    fun solution() {
        input()
        val minCost = dfs(0, 1)
        print(minCost)
    }

    companion object {
        private const val INF = 1_000_000 * 16 + 1
    }
}

fun main() {
    `2098`().solution()
}