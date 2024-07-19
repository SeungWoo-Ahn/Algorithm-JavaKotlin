package boj.minimum_spanning_tree

class `20040` {
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

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        parent = IntArray(n) { it }
        repeat(m) { idx ->
            val (u, v) = readLine().splitToInt()
            if (!union(u, v)) {
                print(idx + 1)
                return
            }
        }
        print(0)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `20040`().solution()
}