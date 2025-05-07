package boj.dp

import java.util.StringTokenizer

class `1949` {
    private lateinit var adj: Array<MutableList<Int>>
    private lateinit var dp: Array<IntArray>
    private lateinit var cntList: IntArray

    private fun dfs(cur: Int, parent: Int) {
        dp[cur][0] = cntList[cur]
        dp[cur][1] = 0
        for (nxt in adj[cur]) {
            if (nxt == parent) continue
            dfs(nxt, cur)
            dp[cur][0] += dp[nxt][1]
            dp[cur][1] += maxOf(dp[nxt][0], dp[nxt][1])
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        adj = Array(n + 1) { mutableListOf() }
        dp = Array(n + 1) { IntArray(2) }
        cntList = IntArray(n + 1)
        var st = StringTokenizer(readLine(), " ")
        for (i in 1 until cntList.size) {
            cntList[i] = st.nextToken().toInt()
        }
        repeat(n - 1) {
            st = StringTokenizer(readLine(), " ")
            val u = st.nextToken().toInt()
            val v = st.nextToken().toInt()
            adj[u] += v
            adj[v] += u
        }
        dfs(1, 0)
        val result = maxOf(dp[1][0], dp[1][1])
        print(result)
    }
}

fun main() {
    `1949`().solution()
}