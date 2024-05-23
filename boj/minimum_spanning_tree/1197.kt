package boj.minimum_spanning_tree

class `1197` {
    private var parent = intArrayOf()

    private fun find(x: Int): Int {
        if (parent[x] < 0) return x
        parent[x]= find(parent[x])
        return parent[x]
    }

    private fun isDiffGroup(x: Int, y: Int): Boolean {
        val u = find(x)
        val v = find(y)
        if (u == v) return false
        if (parent[u] == parent[v]) parent[u]--
        if (parent[u] < parent[v]) parent[v] = u
        else parent[u] = v
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (v, e) = readLine().splitToInt()
        val edge = Array(e) {
            val (a, b, c) = readLine().splitToInt()
            Triple(c, a, b)
        }
        edge.sortBy { it.first }
        parent = IntArray(v + 1) { -1 }
        var cnt = 0
        var answer = 0
        for ((cost, x, y) in edge) {
            if (!isDiffGroup(x, y)) continue
            answer += cost
            if (++cnt == v - 1) break
        }
        println(answer)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1197`().solution()
}