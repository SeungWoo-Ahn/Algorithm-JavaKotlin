package boj.minimum_spanning_tree

import java.util.PriorityQueue
import java.util.StringTokenizer

class `16398` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val check = BooleanArray(n + 1)
        var st: StringTokenizer
        for (i in 1 .. n) {
            st = StringTokenizer(readLine())
            for (j in 1 .. n) {
                val cost = st.nextToken().toInt()
                if (i >= j) continue
                adj[i].add(cost to j)
                adj[j].add(cost to i)
            }
        }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        adj[1].forEach { pq.add(it) }
        check[1] = true
        var cnt = 0
        var answer = 0L
        while (cnt < n - 1) {
            val (cost, node) = pq.poll()
            if (check[node]) continue
            check[node] = true
            answer += cost
            cnt++
            for (nxt in adj[node]) {
                if (!check[nxt.second])
                    pq.add(nxt)
            }
        }
        println(answer)
    }
}

fun main() {
    `16398`().solution()
}