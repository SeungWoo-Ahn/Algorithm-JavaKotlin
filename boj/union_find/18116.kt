package boj.union_find

import java.util.StringTokenizer

class `18116` {
    private val parent = IntArray(1_000_001) { -1 }

    private fun find(x: Int): Int {
        if (parent[x] < 0) {
            return x
        }
        return find(parent[x]).also { parent[x] = it }
    }

    private fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return
        if (parent[rootX] < parent[rootY]) {
            parent[rootX] += parent[rootY]
            parent[rootY] = rootX
        } else {
            parent[rootY] += parent[rootX]
            parent[rootX] = rootY
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val sb = StringBuilder()
        var st: StringTokenizer
        repeat(n) {
            st = StringTokenizer(readLine(), " ")
            when (st.nextToken()) {
                "I" -> {
                    val a = st.nextToken().toInt()
                    val b = st.nextToken().toInt()
                    union(a, b)
                }
                "Q" -> {
                    val c = st.nextToken().toInt()
                    val root = find(c)
                    sb.appendLine(-parent[root])
                }
            }
        }
        print(sb)
    }
}

fun main() {
    `18116`().solution()
}