package programmers.Practice.Level3

class Lighthouse {
    private lateinit var adj: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray
    private lateinit var dp: Array<IntArray>

    private fun dfs(node: Int) {
        visited[node] = true
        dp[node][0] = 1
        for (child in adj[node]) {
            if (visited[child]) continue
            dfs(child)
            dp[node][0] += minOf(dp[child][0], dp[child][1])
            dp[node][1] += dp[child][0]
        }
    }

    private fun init(n: Int, lighthouse: Array<IntArray>) {
        adj = Array(n + 1) { mutableListOf() }
        visited = BooleanArray(n + 1)
        dp = Array(n + 1) { IntArray(2) }
        for ((u, v) in lighthouse) {
            adj[u] += v
            adj[v] += u
        }
    }

    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        init(n, lighthouse)
        dfs(1)
        return minOf(dp[1][0], dp[1][1])
    }
}

fun main() {
    val n = 10
    val lighthouse = arrayOf(
        intArrayOf(4, 1),
        intArrayOf(5, 1),
        intArrayOf(5, 6),
        intArrayOf(7, 6),
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(6, 8),
        intArrayOf(2, 9),
        intArrayOf(9, 10)
    )
    val answer = Lighthouse().solution(n, lighthouse)
    print(answer)
}