package boj.tree

import java.util.StringTokenizer

class `3584` {
    private lateinit var parent: IntArray
    private lateinit var set: HashSet<Int>

    private fun addRoute(node: Int) {
        set += node
        if (parent[node] == 0) {
            return
        }
        addRoute(parent[node])
    }

    private fun findCommonParent(node: Int): Int {
        if (set.contains(node)) {
            return node
        }
        return findCommonParent(parent[node])
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        var st: StringTokenizer
        repeat(t) {
            val n = readLine().toInt()
            parent = IntArray(n + 1)
            set = hashSetOf()
            repeat(n - 1) {
                st = StringTokenizer(readLine(), " ")
                val a = st.nextToken().toInt()
                val b = st.nextToken().toInt()
                parent[b] = a
            }
            st = StringTokenizer(readLine(), " ")
            val first = st.nextToken().toInt()
            val second = st.nextToken().toInt()
            addRoute(first)
            val commonParent = findCommonParent(second)
            sb.appendLine(commonParent)
        }
        print(sb)
    }
}

fun main() {
    `3584`().solution()
}