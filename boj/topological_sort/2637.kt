package boj.topological_sort

import java.util.LinkedList
import java.util.Queue

class `2637` {
    private var inDegree = intArrayOf()
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()
    private var isBasePart = booleanArrayOf()
    private var counts = intArrayOf()

    private fun countEachPart(n: Int) {
        val q = LinkedList<Int>() as Queue<Int>
        for (i in 1 .. n) {
            if (inDegree[i] == 0) {
                q.offer(i)
                counts[i] = 1
            }
        }
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for ((nxt, cnt) in  adj[cur]) {
                counts[nxt] += counts[cur] * cnt
                if (--inDegree[nxt] == 0)
                    q.offer(nxt)
            }
        }
    }

    private fun printResult(n: Int) {
        val sb = StringBuilder()
        for (i in 1 until n) {
            if (isBasePart[i])
                sb.appendLine("$i ${counts[i]}")
        }
        println(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        inDegree = IntArray(n + 1)
        adj = Array(n + 1) { mutableListOf() }
        isBasePart = BooleanArray(n + 1) { true }
        counts = IntArray(n + 1)
        repeat(m) {
            val (x, y, k) = readLine().split(" ").map { it.toInt() }
            inDegree[y]++
            adj[x].add(y to k)
            isBasePart[x] = false
        }
        countEachPart(n)
        printResult(n)
    }
}

fun main() {
    `2637`().solution()
}