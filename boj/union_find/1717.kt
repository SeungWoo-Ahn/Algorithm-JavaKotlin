package boj.union_find

class `1717` {
    private lateinit var parent: IntArray

    private fun find(x: Int): Int {
        if (parent[x] < 0) {
            return x
        }
        parent[x] = find(parent[x])
        return parent[x]
    }

    private fun union(x: Int, y: Int): Boolean {
        var rootX = find(x)
        var rootY = find(y)
        if (rootX == rootY) {
            return false
        }
        if (parent[rootY] < parent[rootX]) {
            val temp = rootX
            rootX = rootY
            rootY = temp
        }
        if (parent[rootX] == parent[rootY]) {
            parent[rootX]--
        }
        parent[rootY] = rootX
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        val sb = StringBuilder()
        parent = IntArray(n + 1) { -1 }
        repeat(m) {
            val (c, a, b) = readLine().splitToInt()
            when (c) {
                0 -> union(a, b)
                1 -> {
                    val result = if (find(a) == find(b)) "YES" else "NO"
                    sb.appendLine(result)
                }
            }
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1717`().solution()
}