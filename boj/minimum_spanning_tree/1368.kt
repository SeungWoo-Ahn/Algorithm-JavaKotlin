package boj.minimum_spanning_tree

import java.util.PriorityQueue
import java.util.StringTokenizer

class `1368` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val check = BooleanArray(n + 1)
        repeat(n) { i ->
            val w = readLine().toInt()
            adj[i + 1].add(w to 0)
            adj[0].add(w to i + 1)
        }
        var st: StringTokenizer
        for (i in 1 .. n) {
            st = StringTokenizer(readLine())
            for (j in 1 .. n) {
                val p = st.nextToken().toInt()
                if (i >= j) continue
                adj[i].add(p to j)
                adj[j].add(p to i)
            }
        }
        var cnt = 0
        var answer = 0
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        check[0] = true
        for (nxt in adj[0]) pq.add(nxt)
        while (cnt < n) {
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
    `1368`().solution()
}