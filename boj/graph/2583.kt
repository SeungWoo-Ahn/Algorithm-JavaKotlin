package boj.graph

import java.lang.StringBuilder
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

class `2583` {
    private val answers = PriorityQueue<Int>()
    private val dx = listOf(1, -1, 0, 0)
    private val dy = listOf(0, 0, 1, -1)

    fun solution() = with(System.`in`.bufferedReader()) {
        val (M, N, K) = readLine().split(" ").map { it.toInt() }
        val map = Array(N) { BooleanArray(M) }.apply {
            repeat(K) {
                val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }
                for (i in x1 until x2) {
                    for(j in y1 until y2) {
                        this[i][j] = true
                    }
                }
            }
        }
        for (i in map.indices) {
            for (j in map[0].indices) {
                if (!map[i][j]) bfs(i, j, map)
            }
        }
        val sb = StringBuilder()
        sb.append("${answers.size}\n")
        while (answers.isNotEmpty()){
            sb.append("${answers.poll()} ")
        }
        println(sb)
    }
    private fun bfs(x: Int, y: Int, map: Array<BooleanArray>) {
        var size = 0
        val q: Queue<Pair<Int, Int>> = LinkedList()
        q.offer(Pair(x, y))
        map[x][y] = true

        while (q.isNotEmpty()) {
            val cur = q.poll()
            size++
            for (i in 0 until 4) {
                val nx = cur.first + dx[i]
                val ny = cur.second + dy[i]
                if (nx !in map.indices || ny !in map[0].indices || map[nx][ny]) continue
                map[nx][ny] = true
                q.offer(Pair(nx, ny))
            }
        }
        answers.offer(size)
    }
}

fun main() {
    `2583`().solution()
}