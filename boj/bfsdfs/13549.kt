package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `13549` {
    private val cost = IntArray(SIZE) { -1 }

    private fun hideAndSeek(n: Int, k: Int): Int {
        if (n >= k) return n - k
        val q = LinkedList<Int>() as Queue<Int>
        q += n
        cost[n] = 0
        while (q.isNotEmpty()) {
            val pos = q.poll()
            if (pos == k) return cost[pos]
            for ((i, newPos) in listOf(pos * 2, pos - 1, pos + 1).withIndex()) {
                if (outOfBoundary(newPos) || cost[newPos] >= 0) continue
                cost[newPos] = if (i == 0) cost[pos] else cost[pos] + 1
                q += newPos
            }
        }
        return cost[k]
    }

    private fun outOfBoundary(pos: Int): Boolean {
        return pos !in 0 until SIZE
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val time = hideAndSeek(n, k)
        print(time)
    }

    companion object {
        private const val SIZE = 100_001
    }
}

fun main() {
    `13549`().solution()
}