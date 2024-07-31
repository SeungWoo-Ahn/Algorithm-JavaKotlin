package programmers.Greedy

class ConnectIsland {
    private var parent = intArrayOf()

    private fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    private fun union(x: Int, y: Int): Boolean {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return false
        if (rootX < rootY) parent[rootY] = rootX
        else parent[rootX] = rootY
        return true
    }

    fun solution(n: Int, costs: Array<IntArray>): Int {
        val sortedCosts = costs.sortedBy { it[2] }
        parent = IntArray(n) { it }
        var connectCnt = 0
        var sum = 0
        for ((u, v, cost) in sortedCosts) {
            if (!union(u, v)) continue
            connectCnt++
            sum += cost
            if (connectCnt == n - 1) break
        }
        return sum
    }
}

fun main() {
    val n = 4
    val costs = arrayOf(
        intArrayOf(0, 1, 1),
        intArrayOf(0, 2, 2),
        intArrayOf(1, 2, 5),
        intArrayOf(1, 3, 1),
        intArrayOf(2, 3, 8),
    )
    val answer = ConnectIsland().solution(n, costs)
    print(answer)
}