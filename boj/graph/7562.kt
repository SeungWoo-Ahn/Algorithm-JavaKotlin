package boj.graph

import java.lang.StringBuilder
import java.util.LinkedList
import java.util.Queue

class `7562` {
    data class Node(val x: Int, val y: Int, val cnt: Int = 0)

    private val sb = StringBuilder()
    private val d = listOf(
        Node(2, 1),
        Node(1, 2),
        Node(-1, 2),
        Node(-2, 1),
        Node(-2, -1),
        Node(-1, -2),
        Node(1, -2),
        Node(2, -1)
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        repeat(t) {
            val size = readLine().toInt()
            val (x1, y1) = readLine().split(" ").map { it.toInt() }
            val (x2, y2) = readLine().split(" ").map { it.toInt() }
            bfs(size, Node(x1, y1), Node(x2, y2))
        }
        println(sb)
    }

    private fun bfs(size: Int, s: Node, e: Node) {
        val visited = Array(size) { BooleanArray(size) { false } }
        val q: Queue<Node> = LinkedList<Node>().apply {
            offer(s)
            visited[s.x][s.y] = true
        }

        var answer = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (cur.x == e.x && cur.y == e.y) {
                answer = cur.cnt
                break
            }
            for (i in 0 until 8) {
                val nx = cur.x + d[i].x
                val ny = cur.y + d[i].y
                if (nx !in 0 until size || ny !in 0 until size || visited[nx][ny]) continue
                q.offer(Node(nx, ny, cur.cnt + 1))
                visited[nx][ny] = true
            }
        }
        sb.append("${answer}\n")
    }
}

fun main() {
    `7562`().solution()
}