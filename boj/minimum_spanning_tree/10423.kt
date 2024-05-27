package boj.minimum_spanning_tree

import java.util.PriorityQueue
import java.util.StringTokenizer

class `10423` {
    private var parent = intArrayOf()
    private var yny = booleanArrayOf()

    private fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    private fun union(x: Int, y: Int): Boolean {
        val xRoot = find(x)
        val yRoot = find(y)
        if (xRoot == yRoot || (yny[xRoot] && yny[yRoot]))
            return false
        if (yny[xRoot]) parent[yRoot] = xRoot
        else if (yny[yRoot]) parent[xRoot] = yRoot
        else if (xRoot < yRoot) parent[yRoot] = xRoot
        else parent[xRoot] = yRoot
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readLine().splitToInt()

        yny = BooleanArray(n + 1)
        val st = StringTokenizer(readLine())
        repeat(k) { yny[st.nextToken().toInt()] = true }

        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        repeat(m) {
            val (u, v, w) = readLine().splitToInt()
            pq += Triple(w, u, v)
        }

        var cnt = k
        var totalCost = 0
        parent = IntArray(n + 1) { it }
        while (cnt < n) {
            val (cost, u, v) = pq.poll()
            if (!union(u, v)) continue
            totalCost += cost
            cnt++
        }
        println(totalCost)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `10423`().solution()
}