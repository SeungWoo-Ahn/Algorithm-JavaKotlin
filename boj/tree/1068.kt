package boj.tree

import java.util.StringTokenizer

class `1068` {
    private var parent = intArrayOf()
    private var child: Array<MutableList<Int>> = arrayOf()
    private var root = 0
    private var leafCnt = 0

    private fun cntLeaf(cur: Int) {
        if (child[cur].isEmpty()) {
            leafCnt++
            return
        }
        for (nxt in child[cur])
            cntLeaf(nxt)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        parent = IntArray(n + 1)
        child = Array(n + 1) { mutableListOf() }
        val st = StringTokenizer(readLine())
        repeat(n) { i ->
            val p = st.nextToken().toInt()
            parent[i] = p
            if (p == ROOT) root = i
            else child[p].add(i)
        }
        val removedNode = readLine().toInt()
        if (removedNode != root) {
            child[parent[removedNode]].remove(removedNode)
            cntLeaf(root)
        }
        println(leafCnt)
    }

    companion object {
        private const val ROOT = -1
    }
}

fun main() {
    `1068`().solution()
}