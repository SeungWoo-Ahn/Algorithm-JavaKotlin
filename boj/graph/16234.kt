package boj.graph

import java.util.Stack
import java.util.StringTokenizer
import kotlin.math.abs

class `16234` {
    private data class Node(val x: Int, val y: Int)

    private var map: Array<IntArray> = arrayOf()
    private val d = listOf(Node(1, 0), Node(-1, 0), Node(0, 1), Node(0 , -1))

    private fun dfs(l: Int, r: Int, startX: Int, startY: Int, visited: Array<BooleanArray>): Boolean {
        val stack = Stack<Node>()
        val unions = mutableListOf<Node>()
        var sum = 0
        stack.push(Node(startX, startY))
        visited[startX][startY] = true
        while (stack.isNotEmpty()) {
            val (x, y) = stack.pop()
            unions.add(Node(x, y))
            sum += map[x][y]
            for (i in d.indices) {
                val nx = x + d[i].x
                val ny = y + d[i].y
                if (nx !in map.indices || ny !in map.indices || visited[nx][ny]) continue
                if (abs(map[x][y] - map[nx][ny]) in l .. r) {
                    stack.push(Node(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }
        val average = sum / unions.size
        unions.forEach { node ->
            map[node.x][node.y] = average
        }
        return unions.size > 1
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, l, r) = readLine().split(" ").map { it.toInt() }
        map = Array(n) {
            val st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        var answer = 0
        while (true) {
            var isChange = false
            val visited = Array(n) { BooleanArray(n) }
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (!visited[i][j]) {
                        if (dfs(l, r, i, j, visited)) isChange = true
                    }
                }
            }
            if (isChange) answer++
            else break
        }
        println(answer)
    }
}

fun main() {
    `16234`().solution()
}