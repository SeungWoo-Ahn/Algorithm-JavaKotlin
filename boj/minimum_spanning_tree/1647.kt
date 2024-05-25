package boj.minimum_spanning_tree

import java.util.PriorityQueue

class `1647` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val check = BooleanArray(n + 1)
        repeat(m) {
            val (a, b, cost) = readLine().splitToInt()
            adj[a].add(cost to b)
            adj[b].add(cost to a)
        }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        adj[1].forEach { pq.add(it) }
        check[1] = true
        var cnt = 0
        var sum = 0
        var max = 0
        while (cnt < n - 1) {
            val (cost, node) = pq.poll()
            if (check[node]) continue
            check[node] = true
            sum += cost
            if (cost > max) max = cost
            cnt++
            for (nxt in adj[node]) {
                if (!check[nxt.second])
                    pq.add(nxt)
            }
        }
        println(sum - max)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1647`().solution()
}