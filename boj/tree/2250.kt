package boj.tree

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

class `2250` {
    private var parent = intArrayOf()
    private var lc = intArrayOf()
    private var rc = intArrayOf()
    private var posByLevel: Array<IntArray> = arrayOf()
    private var seq = 0
    private var maxLevel = 0
    private val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ -it.second }, { it.first }))

    private fun findRoot(cur: Int) {
        if (parent[cur] == 0) {
            inOrder(cur)
            return
        }
        else findRoot(parent[cur])
    }

    private fun inOrder(cur: Int, level: Int = 1) {
        if (lc[cur] != EMPTY) inOrder(lc[cur], level + 1)
        maxLevel = max(maxLevel, level)
        seq++
        posByLevel[level][0] = min(posByLevel[level][0], seq)
        posByLevel[level][1] = max(posByLevel[level][1], seq)
        if (rc[cur] != EMPTY) inOrder(rc[cur], level + 1)
    }

    private fun findWidthByLevel() {
        for (level in 1 .. maxLevel) {
            val width = posByLevel[level][1] - posByLevel[level][0] + 1
            pq.add(level to width)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        parent = IntArray(n + 1)
        lc = IntArray(n + 1)
        rc = IntArray(n + 1)
        posByLevel = Array(n + 1) {
            IntArray(2) { i ->
                if (i == 0) n
                else 0
            }
        }
        var leaf = EMPTY
        repeat(n) {
            val (node, l, r) = readLine().split(" ").map { it.toInt() }
            if (l == EMPTY && r == EMPTY && leaf == EMPTY)
                leaf = node
            if (l != EMPTY) parent[l] = node
            if (r != EMPTY) parent[r] = node
            lc[node] = l
            rc[node] = r
        }
        findRoot(leaf)
        findWidthByLevel()
        println("${pq.peek().first} ${pq.peek().second}")
    }

    companion object {
        private const val EMPTY = -1
    }
}

fun main() {
    `2250`().solution()
}