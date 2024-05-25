package boj.minimum_spanning_tree

import java.util.PriorityQueue

class `13418` {
    private val upHillPq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
    private val downHillPq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { -it.first })
    private var parent = intArrayOf()

    private fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    private fun union(x: Int, y: Int): Boolean {
        val xRoot = find(x)
        val yRoot = find(y)
        if (xRoot == yRoot) return false
        if (xRoot < yRoot) parent[yRoot] = xRoot
        else parent[xRoot] = yRoot
        return true
    }

    private fun PriorityQueue<Triple<Int, Int, Int>>.kruskal(n: Int): Int {
        parent = IntArray(n + 1) { it }
        var k = 0
        var cnt = 0
        while (cnt < n) {
            val (road, u, v) = poll()
            if (!union(u, v)) continue
            if (road == UP_HILL) k++
            cnt++
        }
        return k * k
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        repeat(m + 1) {
            val (a, b, c) = readLine().splitToInt()
            Triple(c, a, b).let {
                upHillPq += it
                downHillPq += it
            }
        }
        val worstCase = upHillPq.kruskal(n)
        val bestCase = downHillPq.kruskal(n)
        println(worstCase - bestCase)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val UP_HILL = 0
    }
}

fun main() {
    `13418`().solution()
}