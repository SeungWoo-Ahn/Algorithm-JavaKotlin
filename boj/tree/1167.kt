package boj.tree

import java.util.StringTokenizer

class `1167` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()
    private var maxDis = 0
    private var furthestNode = 0

    private fun dfs(node: Int, sum: Int, visited: BooleanArray) {
        visited[node] = true
        for ((nxt, dis) in adj[node]) {
            if (visited[nxt]) continue
            dfs(nxt, sum + dis, visited)
        }
        if (sum > maxDis) {
            maxDis = sum
            furthestNode = node
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        adj = Array(n + 1) { mutableListOf() }
        var st: StringTokenizer
        repeat(n) {
            st = StringTokenizer(readLine())
            val u = st.nextToken().toInt()
            while (true) {
                val v = st.nextToken().toInt()
                if (v == -1) break
                val dis = st.nextToken().toInt()
                if (v > u) {
                    adj[u] += v to dis
                    adj[v] += u to dis
                }
            }
        }
        dfs(1, 0, BooleanArray(n + 1))
        dfs(furthestNode, 0, BooleanArray(n + 1))
        print(maxDis)
    }
}

fun main() {
    `1167`().solution()
}
