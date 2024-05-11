package boj.tree

import java.util.LinkedList
import java.util.Queue

class `1240` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n , m) = readLine().splitToInt()
        val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        repeat(n - 1) {
            val (u, v, d) = readLine().splitToInt()
            adj[u].add(v to d)
            adj[v].add(u to d)
        }
        val sb = StringBuilder()
        repeat(m) {
            val (u, v) = readLine().splitToInt()
            val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
            val visited = BooleanArray(n + 1)
            q.offer(u to 0)
            visited[u] = true
            while (q.isNotEmpty()) {
                val (cur, dis) = q.poll()
                if (cur == v) {
                    sb.appendLine(dis)
                    break
                }
                for ((nxt, nxtDis) in adj[cur]) {
                    if (visited[nxt]) continue
                    visited[nxt] = true
                    q.offer(nxt to dis + nxtDis)
                }
            }
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1240`().solution()
}