package boj.minimum_spanning_tree

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

class `1774` {
    private var pos: Array<Pair<Int, Int>> = arrayOf()
    private var parent = intArrayOf()

    private fun calcDistance(u: Int, v: Int): Double {
        val (uX, uY) = pos[u]
        val (vX, vY) = pos[v]
        val xDis = (uX - vX).toDouble()
        val yDis = (uY - vY).toDouble()
        return sqrt(xDis.pow(2) + yDis.pow(2))
    }

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
        pos = Array(n) {
            val (x, y) = readLine().splitToInt()
            x to y
        }
        val edge = PriorityQueue<Triple<Double, Int, Int>>(compareBy { it.first })
        for (i in pos.indices) {
            for (j in i + 1 until n) {
                edge += Triple(calcDistance(i, j), i, j)
            }
        }
        parent = IntArray(n) { it }
        var cnt = 0
        repeat(m) {
            val (u, v) = readLine().splitToInt(offset = 1)
            if (union(u, v)) cnt++
        }
        var answer = 0.0
        while (cnt < n - 1) {
            val (dis, u, v) = edge.poll()
            if (!union(u, v)) continue
            answer += dis
            cnt++
        }
        println(String.format("%.2f", answer))
    }

    private fun String.splitToInt(offset: Int = 0) = split(" ").map { it.toInt() - offset }
}

fun main() {
    `1774`().solution()
}