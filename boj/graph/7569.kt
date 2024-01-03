package boj.graph

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `7569` {
    private val dx = listOf(0, 0, -1, 1, 0, 0)
    private val dy = listOf(0, 0, 0, 0, 1, -1)
    private val dz = listOf(1, -1, 0, 0, 0, 0)

    data class Node(
        val x: Int,
        val y: Int,
        val z: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val (M, N, H) = readLine().split(" ").map { it.toInt() }
        val startNodes: Queue<Node> = LinkedList()
        val box = Array(M) { Array(N) { IntArray(H) { 0 } } }.apply {
            repeat(H) { z ->
                repeat(N) { y ->
                    val st = StringTokenizer(readLine()," ")
                    repeat(M) { x ->
                        st.nextToken().toInt().let {
                            this[x][y][z] = it
                            if (it == 1) startNodes.offer(Node(x, y, z))
                        }
                    }
                }
            }
        }
        var answer = 0
        val q: Queue<Node> = LinkedList()
        val checkNode = Node(-1, -1, -1)
        startNodes.forEach { q.offer(it) }
        q.offer(checkNode)
        while (q.isNotEmpty()) {
            val node = q.poll()
            if (node.x == -1 && node.y == -1 && node.z == -1) {
                answer++
                if (q.isNotEmpty()) {
                    q.offer(checkNode)
                }
            }
            for (i in 0 until 6) {
                val newX = node.x + dx[i]
                val newY = node.y + dy[i]
                val newZ = node.z + dz[i]
                if (newX !in 0 until M || newY !in 0 until N || newZ !in 0 until H || box[newX][newY][newZ] != 0) {
                    continue
                }
                box[newX][newY][newZ] = 1
                q.offer(Node(newX, newY, newZ))
            }
        }
        box.forEach { it ->
            it.forEach { it2 ->
                it2.forEach { it3 ->
                    if (it3 == 0) {
                        println(-1)
                        return
                    }
                }
            }
        }
        println(answer - 1)
    }
}

fun main() {
    `7569`().solution()
}