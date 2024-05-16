package boj.tree

import kotlin.math.max
import kotlin.math.min

class `2250` {
    private var parent = intArrayOf()
    private var lc = intArrayOf()
    private var rc = intArrayOf()
    private var leftPos = intArrayOf()
    private var rightPos = intArrayOf()
    private var seq = 0

    private fun findRoot(n: Int): Int {
        for (i in 1 .. n) {
            if (parent[i] == 0)
                return i
        }
        return 0
    }

    private fun inOrder(cur: Int, level: Int = 1) {
        if (lc[cur] != EMPTY) inOrder(lc[cur], level + 1)
        seq++
        leftPos[level] = min(leftPos[level], seq)
        rightPos[level] = max(rightPos[level], seq)
        if (rc[cur] != EMPTY) inOrder(rc[cur], level + 1)
    }

    private fun findMaxWidth(n: Int): Pair<Int, Int> {
        var minLevel = 1
        var maxWidth = 1
        for (level in 1 .. n) {
            val width = rightPos[level] - leftPos[level] + 1
            if (width > maxWidth) {
                minLevel = level
                maxWidth = width
            }
        }
        return minLevel to maxWidth
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        parent = IntArray(n + 1)
        lc = IntArray(n + 1)
        rc = IntArray(n + 1)
        leftPos = IntArray(n + 1) { n }
        rightPos = IntArray(n + 1)
        repeat(n) {
            val (node, left, right) = readLine().split(" ").map { it.toInt() }
            if (left != EMPTY) parent[left] = node
            if (right != EMPTY) parent[right] = node
            lc[node] = left
            rc[node] = right
        }
        val root = findRoot(n)
        inOrder(root)
        val (level, width) = findMaxWidth(n)
        println("$level $width")
    }

    companion object {
        private const val EMPTY = -1
    }
}

fun main() {
    `2250`().solution()
}