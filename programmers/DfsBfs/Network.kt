package programmers.DfsBfs

import java.util.LinkedList
import java.util.Queue

class Network {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var visited = booleanArrayOf()

    private fun bfs(st: Int) {
        val q = LinkedList<Int>() as Queue<Int>
        q += st
        visited[st] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (nxt in adj[cur]) {
                if (visited[nxt]) continue
                visited[nxt] = true
                q += nxt
            }
        }
    }

    fun solution(n: Int, computers: Array<IntArray>): Int {
        adj = Array(n) { mutableListOf() }
        visited = BooleanArray(n)
        for (u in 0 until n) {
            for (v in u + 1 until n) {
                if (computers[u][v] == 1) {
                    adj[u] += v
                    adj[v] += u
                }
            }
        }
        var networkCnt = 0
        for (i in 0 until n) {
            if (visited[i]) continue
            bfs(i)
            networkCnt++
        }
        return networkCnt
    }
}

fun main() {
    val n = 3
    val computers = arrayOf(
        intArrayOf(1, 1, 0),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 1, 1),
    )
    val answer = Network().solution(n, computers)
    print(answer)
}