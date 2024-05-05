package boj.graph

import kotlin.math.max

class `1325` {
    private var graph: Array<MutableList<Int>> = arrayOf()
    private var cntArr = intArrayOf()
    private var visited = booleanArrayOf()
    private var max = 0

    private fun dfs(st: Int) {
        visited[st] = true
        cntArr[st]++
        max = max(max, cntArr[st])
        for (nxt in graph[st]) {
            if (visited[nxt]) continue
            dfs(nxt)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        graph = Array(n + 1) { mutableListOf() }
        cntArr = IntArray(n + 1)
        visited = BooleanArray(n + 1)
        repeat(m) {
            val (a, b) = readLine().splitToInt()
            graph[a].add(b)
        }
        for (i in 1 .. n) {
            visited = BooleanArray(n + 1)
            dfs(i)
        }
        val sb = StringBuilder()
        for (i in 1 .. n) {
            if (cntArr[i] == max)
                sb.append("$i ")
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1325`().solution()
}