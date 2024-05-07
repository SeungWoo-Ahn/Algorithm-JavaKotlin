package boj.graph

import java.util.LinkedList
import java.util.Queue

class `2617` {
    private var heavyGraph: Array<MutableList<Int>> = arrayOf()
    private var lightGraph: Array<MutableList<Int>> = arrayOf()
    private var visited = booleanArrayOf()

    private fun bfs(n: Int, start: Int, graph: Array<MutableList<Int>>): Boolean {
        visited = BooleanArray(n + 1)
        val q = LinkedList<Int>() as Queue<Int>
        q.offer(start)
        visited[start] = true
        var cnt = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (adj in graph[cur]) {
                if (visited[adj]) continue
                visited[adj] = true
                q.offer(adj)
                cnt++
            }
        }
        return cnt >= (n + 1) / 2
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        heavyGraph = Array(n + 1) { mutableListOf() }
        lightGraph = Array(n + 1) { mutableListOf() }
        repeat(m) {
            val (heavier, lighter) = readLine().splitToInt()
            heavyGraph[heavier].add(lighter)
            lightGraph[lighter].add(heavier)
        }
        var answer = 0
        for (i in 1 .. n) {
            if (bfs(n, i, heavyGraph) || bfs(n, i, lightGraph))
                answer++
        }
        println(answer)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `2617`().solution()
}