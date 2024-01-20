package boj.graph

import java.lang.StringBuilder
import java.util.LinkedList
import java.util.Queue

class `13913` {

    private val visited = IntArray(200_001) { -1 }

    private fun movePos(pos: Int): IntArray {
        val arr = IntArray(3)
        arr[0] = pos * 2
        arr[1] = pos - 1
        arr[2] = pos + 1
        return arr
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map { it.toInt() }
        val sb = StringBuilder()
        sb.append("${bfs(N, K)}\n")
        val history = mutableListOf(K)
        var next = K
        if (N != K) {
            while (true) {
                history.add(visited[next])
                next = visited[next]
                if (next == N || next == -1) break
            }
        }
        for (i in history.size - 1 downTo 0) {
            sb.append("${history[i]} ")
        }
        println(sb)
    }

    private fun bfs(n: Int, k: Int): Int {
        val q: Queue<Int> = LinkedList<Int>().apply { offer(n) }
        var cnt = 0
        if (n == k) return 0
        while (q.isNotEmpty()) {
            val size = q.size
            for (i in 0 until size) {
                val cur = q.poll()
                val nextPos = movePos(cur)
                for (j in 0 until 3) {
                    val nP = nextPos[j]
                    if (nP == k) {
                        visited[nP] = cur
                        return cnt + 1
                    }
                    if (nP !in 0 until 200_001 || visited[nP] != -1) continue
                    visited[nP] = cur
                    q.offer(nP)
                }
            }
            cnt++
        }
        return 0
    }

}

fun main() {
    `13913`().solution()
}