package boj.graph

import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

class `6118` {
    private var graph: Array<MutableList<Int>> = arrayOf()
    private var dis = intArrayOf()
    private var visited = booleanArrayOf()

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        graph = Array(n + 1) { mutableListOf() }
        dis = IntArray(n + 1)
        visited = BooleanArray(n + 1)
        val q = LinkedList<Int>() as Queue<Int>
        q.offer(1)
        visited[1] = true
        repeat(m) {
            val (a, b) = readLine().splitToInt()
            graph[a].add(b)
            graph[b].add(a)
        }
        var maxDis = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            maxDis = max(maxDis, dis[cur])
            for (adj in graph[cur]) {
                if (visited[adj]) continue
                visited[adj] = true
                dis[adj] = dis[cur] + 1
                q.offer(adj)
            }
        }
        val answers = mutableListOf<Int>()
        for (i in 1 .. n) {
            if (dis[i] == maxDis)
                answers.add(i)
        }
        println("${answers[0]} $maxDis ${answers.size}")
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `6118`().solution()
}