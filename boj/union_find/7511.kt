package boj.union_find

class `7511` {
    private lateinit var parent: IntArray

    private fun find(x: Int): Int {
        if (parent[x] == x) {
            return x
        }
        parent[x] = find(parent[x])
        return parent[x]
    }

    private fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return
        if (rootX < rootY) parent[rootY] = rootX
        else parent[rootX] = rootY
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) { i ->
            sb.appendLine("Scenario ${i + 1}:")
            val n = readLine().toInt()
            val k = readLine().toInt()
            parent = IntArray(n + 1) { it }
            repeat(k) {
                val (a, b) = readLine().splitToInt()
                union(a, b)
            }
            val m = readLine().toInt()
            repeat(m) {
                val (u, v) = readLine().splitToInt()
                val result = if (find(u) == find(v)) 1 else 0
                sb.appendLine(result)
            }
            sb.appendLine()
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `7511`().solution()
}