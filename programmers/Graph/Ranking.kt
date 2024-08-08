package programmers.Graph

class Ranking {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var winCnt = intArrayOf()
    private var loseCnt = intArrayOf()
    private var visited = booleanArrayOf()

    private fun dfs(base: Int, cur: Int) {
        visited[cur] = true
        if (cur != base) {
            loseCnt[base]++
            winCnt[cur]++
        }
        for (nxt in adj[cur]) {
            if (visited[nxt]) continue
            dfs(base, nxt)
        }
    }

    fun solution(n: Int, results: Array<IntArray>): Int {
        adj = Array(n + 1) { mutableListOf() }
        winCnt = IntArray(n + 1)
        loseCnt = IntArray(n + 1)
        for (r in results) {
            adj[r[1]] += r[0]
        }
        for (i in 1..n) {
            visited = BooleanArray(n + 1)
            dfs(i, i)
        }
        var answer = 0
        for (i in 1..n) {
            if (winCnt[i] + loseCnt[i] == n - 1) {
                answer++
            }
        }
        return answer
    }
}

fun main() {
    val n = 5
    val results = arrayOf(
        intArrayOf(4, 3),
        intArrayOf(4, 2),
        intArrayOf(3, 2),
        intArrayOf(1, 2),
        intArrayOf(2, 5)
    )
    val answer = Ranking().solution(n, results)
    print(answer)
}