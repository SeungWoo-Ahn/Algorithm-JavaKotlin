package boj.tree

class `15681` {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var child: Array<MutableList<Int>> = arrayOf()
    private var parent = intArrayOf()
    private var childCnt = intArrayOf()

    private fun makeTree(cur: Int, prev: Int) {
        for (nxt in adj[cur]) {
            if (nxt == prev) continue
            parent[nxt] = cur
            child[cur].add(nxt)
            makeTree(nxt, cur)
        }
    }

    private fun countChild(cur: Int) {
        childCnt[cur] = 1
        for (nxt in child[cur]) {
            countChild(nxt)
            childCnt[cur] += childCnt[nxt]
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, r, q) = readLine().splitToInt()
        adj = Array(n + 1) { mutableListOf() }
        child = Array(n + 1) { mutableListOf() }
        parent = IntArray(n + 1)
        childCnt = IntArray(n + 1)
        repeat(n - 1) {
            val (u, v) = readLine().splitToInt()
            adj[u].add(v)
            adj[v].add(u)
        }
        makeTree(r, -1)
        countChild(r)
        val sb = StringBuilder()
        repeat(q) {
            val u = readLine().toInt()
            sb.appendLine(childCnt[u])
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `15681`().solution()
}