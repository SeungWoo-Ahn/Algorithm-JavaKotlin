package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `14940` {
    private data class Node(val x: Int, val y: Int)

    private var map: Array<IntArray> = arrayOf()
    private var dis: Array<IntArray> = arrayOf()
    private var visited: Array<BooleanArray> = arrayOf()
    private val d = listOf(
        Node(1, 0),
        Node(-1, 0),
        Node(0, 1),
        Node(0, -1),
    )

    private fun bfs(target: Node) {
        val q = LinkedList<Node>() as Queue<Node>
        q += target
        dis[target.x][target.y] = 0
        visited[target.x][target.y] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for ((dx, dy) in d) {
                val nx = x + dx
                val ny = y + dy
                if (!canMove(nx, ny)) continue
                visited[nx][ny] = true
                dis[nx][ny] = dis[x][y] + 1
                q += Node(nx, ny)
            }
        }
    }

    private fun canMove(x: Int, y: Int): Boolean {
        return !outOfBoundary(x, y) && !visited[x][y] && map[x][y] == 1
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun printDis() {
        val sb = StringBuilder()
        for (i in dis.indices) {
            sb.appendLine(dis[i].joinToString(" "))
        }
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        var target = Node(0, 0)
        dis = Array(n) { IntArray(m) { -1 } }
        visited = Array(n) { BooleanArray(m) }
        map = Array(n) { x ->
            st = StringTokenizer(readLine())
            IntArray(m) { y ->
                var num = st.nextToken().toInt()
                when (num) {
                    0 -> dis[x][y] = 0
                    2 -> {
                        target = Node(x, y)
                        num = 1
                    }
                }
                num
            }
        }
        bfs(target)
        printDis()
    }
}

fun main() {
    `14940`().solution()
}