package boj.dijkstra

import java.util.PriorityQueue
import java.util.Stack

class `11779` {
    private var adj: Array<MutableList<Pair<Int, Int>>> = arrayOf()
    private var d = intArrayOf()
    private var pre = intArrayOf()
    private val path = Stack<Int>()

    private fun dijkstra(st: Int) {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        d[st] = 0
        pq.add(d[st] to st)
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.poll()
            if (d[node] != cost) continue
            for ((nxtCost, nxtNode) in adj[node]) {
                if (d[nxtNode] <= d[node] + nxtCost) continue
                d[nxtNode] = d[node] + nxtCost
                pq.add(d[nxtNode] to nxtNode)
                pre[nxtNode] = node
            }
        }
    }

    private fun findPath(st: Int, en: Int) {
        var cur = en
        while (cur != st) {
            path.push(cur)
            cur = pre[cur]
        }
        path.push(st)
    }

    private fun printResult(en: Int) {
        val sb = StringBuilder()
        sb.appendLine(d[en])
        sb.appendLine(path.size)
        while (path.isNotEmpty()) sb.append("${path.pop()} ")
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        adj = Array(n + 1) { mutableListOf() }
        d = IntArray(n + 1) { INF }
        pre = IntArray(n + 1)
        repeat(m) {
            val (u, v, cost) = readLine().splitToInt()
            adj[u] += cost to v
        }
        val (st, en) = readLine().splitToInt()
        dijkstra(st)
        findPath(st, en)
        printResult(en)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 90_000_000
    }
}

fun main() {
    `11779`().solution()
}