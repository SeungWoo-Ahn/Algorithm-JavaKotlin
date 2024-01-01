package boj.graph

import java.lang.StringBuilder
import java.util.LinkedList
import java.util.Queue

class `1260` {

    private var graph = Array(0) { BooleanArray(0) { false } }
    private var visited = BooleanArray(0) { false }
    private val sb = StringBuilder()

    private fun dfs(nodeNum: Int, N: Int) {
        visited[nodeNum] = true
        sb.append("$nodeNum ")
        for (i in 1..N) {
            if (graph[nodeNum][i] && !visited[i]) {
                dfs(i, N)
            }
        }
    }

    private fun bfs(nodeNum: Int, N: Int) {
        val q: Queue<Int> = LinkedList()
        q.offer(nodeNum)
        visited[nodeNum] = true
        sb.append("$nodeNum ")
        while (!q.isEmpty()) {
            val temp = q.poll()
            for(i in 1..N) {
                if (graph[temp][i] && !visited[i]) {
                    q.offer(i)
                    visited[i] = true
                    sb.append("$i ")
                }
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M, V) = readLine().split(" ").map { it.toInt() }
        visited = BooleanArray(N + 1) { false }
        graph = Array(N + 1) { BooleanArray(N + 1) { false } }.apply {
            repeat(M) {
                val (x, y) = readLine().split(" ").map { it.toInt() }
                this[x][y] = true
                this[y][x] = true
            }
        }
        dfs(V, N)
        sb.append("\n")
        visited = BooleanArray(N + 1) { false }
        bfs(V, N)
        println(sb)
    }
}

fun main() {
    `1260`().solution()
}