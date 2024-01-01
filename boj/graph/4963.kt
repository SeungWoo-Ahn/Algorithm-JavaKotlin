package boj.graph

import java.lang.StringBuilder
import java.util.LinkedList
import java.util.Queue

class `4963` {

    private var w = 0
    private var h = 0
    private var map = Array(0) { IntArray(0) { 0 } }
    private var visited = Array(0) { BooleanArray(0) { false } }
    private val dx = listOf(1, 1, 0, -1, -1, -1, 0, 1)
    private val dy = listOf(0, 1, 1, 1, 0, -1, -1, -1)
    private var answer = 0
    private val sb = StringBuilder()

    data class Node(
        val x: Int,
        val y: Int
    )

    private fun checkNotVisited(): Node? {
        for (i in 0 until w) {
            for (j in 0 until h) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    return Node(i, j)
                }
            }
        }
        return null
    }

    private fun bfs(node: Node) {
        val q: Queue<Node> = LinkedList<Node>().apply {
            offer(node)
            visited[node.x][node.y] = true
        }
        while (!q.isEmpty()) {
            val (x, y) = q.poll()
            for (i in dx.indices) {
                val newX = x + dx[i]
                val newY = y + dy[i]
                if (newX < 0 || newX >= w || newY < 0 || newY >= h) continue
                if (map[newX][newY] == 1 && !visited[newX][newY]) {
                    q.offer(Node(newX, newY))
                    visited[newX][newY] = true
                }
            }
        }
        answer++
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        while (true) {
            val (width, height) = readLine().split(" ").map { it.toInt() }
            if (width == 0 && height == 0) break
            w = width
            h = height
            visited = Array(w) { BooleanArray(h) { false } }
            map = Array(w) { IntArray(h) { 0 } }.apply {
                for (j in 0 until h) {
                    val line = readLine().split(" ").map { it.toInt() }
                    for(i in line.indices) {
                        this[i][j] = line[i]
                    }
                }
            }
            answer = 0
            while (checkNotVisited() !== null) {
                bfs(checkNotVisited()!!)
            }
            sb.append(answer).append("\n")
        }
        println(sb)
    }
}

fun main() {
    `4963`().solution()
}