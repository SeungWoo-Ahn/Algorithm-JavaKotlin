package boj.minimum_spanning_tree

import java.util.PriorityQueue

class `1647` {
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
        val edge = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        parent = IntArray(n + 1) { it }
        repeat(m) {
            val (a, b, cost) = readLine().splitToInt()
            edge.add(Triple(cost, a, b))
        }
        var cnt = 0
        var sum = 0
        while (cnt < n - 2) {
            val (cost, u, v) = edge.poll()
            if (!union(u, v)) continue
            sum += cost
            cnt++
        }
        println(sum)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1647`().solution()
}