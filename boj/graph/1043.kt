package boj.graph

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `1043` {
    private val party = Array(52) { mutableListOf<Int>() }
    private val adj = Array(52) { mutableListOf<Int>() }
    private val knowTruth = BooleanArray(52)

    private fun bfs(n: Int) {
        val q = LinkedList<Int>() as Queue<Int>
        for (i in 1 .. n) {
            if (knowTruth[i])
                q.offer(i)
        }
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (nxt in adj[cur]) {
                if (knowTruth[nxt]) continue
                knowTruth[nxt] = true
                q.offer(nxt)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        st = StringTokenizer(readLine())
        repeat(st.nextToken().toInt()) {
            knowTruth[st.nextToken().toInt()] = true
        }
        repeat(m) { idx ->
            st = StringTokenizer(readLine())
            val size = st.nextToken().toInt()
            var prev = st.nextToken().toInt()
            var nxt = 0
            party[idx].add(prev)
            repeat(size - 1) {
                nxt = st.nextToken().toInt()
                party[idx].add(nxt)
                adj[prev].add(nxt)
                adj[nxt].add(prev)
                prev = nxt
            }
        }
        bfs(n)
        var cnt = 0
        for (i in 0 until m) {
            val known = party[i].any { knowTruth[it] }
            if (!known) cnt++
        }
        println(cnt)
    }
}

fun main() {
    `1043`().solution()
}