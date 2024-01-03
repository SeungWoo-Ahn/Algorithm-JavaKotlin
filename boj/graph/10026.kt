package boj.graph

import java.util.LinkedList
import java.util.Queue

class `10026` {
    enum class Color {
        R, G, B, None
    }
    data class Node(val x: Int, val y: Int)

    private val dx = listOf(-1, 1, 0, 0)
    private val dy = listOf(0, 0, -1, 1)
    private var answer1 = 0
    private var answer2 = 0

    private fun bfs1(N: Int, startNode: Node, drawing: Array<Array<Color>>, visited: Array<BooleanArray>) {
        val q: Queue<Node> = LinkedList<Node>().apply {
            offer(startNode)
            visited[startNode.x][startNode.y] = true
        }
        val targetColor = drawing[startNode.x][startNode.y]
        while (q.isNotEmpty()) {
            val node = q.poll()
            for (i in 0 until 4) {
                val newX = node.x + dx[i]
                val newY = node.y + dy[i]
                if (newX !in 0 until N || newY !in 0 until N || visited[newX][newY]) {
                    continue
                }
                if (drawing[newX][newY] == targetColor) {
                    q.offer(Node(newX, newY))
                    visited[newX][newY] = true
                }
            }
        }
        answer1++
    }

    private fun bfs2(N: Int, startNode: Node, drawing: Array<Array<Color>>, visited: Array<BooleanArray>) {
        val q: Queue<Node> = LinkedList<Node>().apply {
            offer(startNode)
            visited[startNode.x][startNode.y] = true
        }
        val isBlue = drawing[startNode.x][startNode.y] == Color.B
        while (q.isNotEmpty()) {
            val node = q.poll()
            for (i in 0 until 4) {
                val newX = node.x + dx[i]
                val newY = node.y + dy[i]
                if (newX !in 0 until N || newY !in 0 until N || visited[newX][newY]) {
                    continue
                }
                if ((isBlue && drawing[newX][newY] == Color.B) || (!isBlue && drawing[newX][newY] != Color.B)) {
                    q.offer(Node(newX, newY))
                    visited[newX][newY] = true
                }
            }
        }
        answer2++
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val drawing = Array(N) { Array<Color>(N) { Color.None } }.apply {
            repeat(N) { y ->
                val line = readLine().toCharArray()
                repeat(N) { x ->
                    val value = when(line[x]) {
                        'R' -> Color.R
                        'G' -> Color.G
                        'B' -> Color.B
                        else -> Color.None
                    }
                    this[x][y] = value
                }
            }
        }
        val visited1 = Array(N) { BooleanArray(N) { false } }
        val visited2 = Array(N) { BooleanArray(N) { false } }
        repeat(N) { y ->
            repeat(N) { x ->
                if (!visited1[x][y]) {
                    bfs1(N, Node(x, y), drawing, visited1)
                }
                if (!visited2[x][y]) {
                    bfs2(N, Node(x, y), drawing, visited2)
                }
            }
        }
        println("$answer1 $answer2")
    }
}

fun main() {
    `10026`().solution()
}