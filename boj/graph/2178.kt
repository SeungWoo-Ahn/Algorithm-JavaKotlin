package boj.graph

import java.util.LinkedList
import java.util.Queue

class `2178` {
    private val dx = listOf(-1, 1, 0, 0)
    private val dy = listOf(0, 0, -1, 1)

    data class Node(
        val x: Int,
        val y: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
         val miro = Array(N + 1) { IntArray(M + 1) { 0 } }.apply {
            for (i in 1..N) {
                val line = readLine().toCharArray()
                for(j in 1..line.size) {
                    this[i][j] = line[j - 1].digitToInt()
                }
            }
        }
        val visited = Array(N + 1) { BooleanArray(M + 1) { false } }
        val q: Queue<Node> = LinkedList()
        q.offer(Node(1, 1))
        visited[1][1] = true
        while (!q.isEmpty()) {
            val (x, y) = q.poll()
            for (i in dx.indices) {
                val newX = x + dx[i]
                val newY = y + dy[i]
                if (newX < 1 || newX > N || newY < 1 || newY > M) continue
                if (miro[newX][newY] == 1 && !visited[newX][newY]) {
                    q.offer(Node(newX, newY))
                    visited[newX][newY] = true
                    miro[newX][newY] = miro[x][y] + 1
                    if (visited[N][M]) break
                }
            }
        }
        println(miro[N][M])
    }
}

fun main() {
    `2178`().solution()
}