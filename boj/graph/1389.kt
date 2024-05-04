package boj.graph

import kotlin.math.min

class `1389` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        val graph = Array(n + 1) { x ->
            IntArray(n + 1) { y ->
                if (x == y) 0
                else Int.MAX_VALUE
            }
        }
        repeat(m) {
            val (a, b) = readLine().splitToInt()
            graph[a][b] = 1
            graph[b][a] = 1
        }
        for (k in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1 .. n) {
                    if (graph[i][k] == Int.MAX_VALUE || graph[k][j] == Int.MAX_VALUE)
                        continue
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
                }
            }
        }
        val score = IntArray(n) { i -> graph[i + 1].filter { it != Int.MAX_VALUE }.sum() }
        val minScore = score.min()
        println(score.indexOfFirst { it == minScore } + 1)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1389`().solution()
}