package boj.backtracking

import java.util.LinkedList
import java.util.Queue

class `1941` {
    class Node(val x: Int, val y: Int)

    private var map: Array<CharArray> = arrayOf()
    private var visited = BooleanArray(25)
    private val d = listOf(Node(1, 0), Node(-1, 0), Node(0, 1), Node(0, -1))
    private var cnt = 0

    private fun backtracking(depth: Int, sCnt: Int, startIdx: Int) {
        if (depth == 7) {
            if (sCnt >= 4 && bfs()) cnt++
            return
        }
        for (i in startIdx until 25) {
            val x = i / 5
            val y = i % 5
            visited[i] = true
            if (map[x][y] == 'S') backtracking(depth + 1, sCnt + 1, i + 1)
            else backtracking(depth + 1, sCnt, i + 1)
            visited[i]= false
        }
    }

    private fun bfs(): Boolean {
        val q: Queue<Node> = LinkedList()
        for (i in 0 until 25) {
            if (visited[i]) {
                q.offer(Node(i / 5, i % 5))
                break
            }
        }
        val bfsVisited = Array(5) { BooleanArray(5) }
        bfsVisited[q.peek().x][q.peek().y] = true
        var cnt = 1
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (i in 0 until 4) {
                val nx = cur.x + d[i].x
                val ny = cur.y + d[i].y
                if (nx !in 0 until 5 || ny !in 0 until 5 || bfsVisited[nx][ny] || !visited[nx * 5 + ny]) continue
                q.offer(Node(nx, ny))
                bfsVisited[nx][ny] = true
                cnt++
            }
        }
        return cnt == 7
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        map = Array(5) { readLine().toCharArray() }
        backtracking(0, 0, 0)
        println(cnt)
    }
}

fun main() {
    `1941`().solution()
}