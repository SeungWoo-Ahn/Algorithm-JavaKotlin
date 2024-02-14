package boj.simulation

import java.util.LinkedList
import java.util.Queue

class `11559` {
    data class Node(val x: Int, val y: Int)

    private var field: Array<CharArray> = arrayOf()
    private val d = listOf(Node(1, 0), Node(-1, 0), Node(0, 1), Node(0, -1))
    private var topRowIdx = 0
    private var answer = 0

    private fun check(): Boolean {
        val visited = Array(12) { BooleanArray(6) }
        var isPop = false
        for (i in topRowIdx until 12) {
            for (j in 0 until 6) {
                if (field[i][j] != '.' && !visited[i][j]) {
                    val result = pop(field[i][j], i, j, visited)
                    if (!isPop) isPop = result
                }
            }
        }
        return isPop
    }

    private fun pop(target: Char, x: Int, y: Int, visited: Array<BooleanArray>): Boolean {
        val q = LinkedList<Node>() as Queue<Node>
        val popList = arrayListOf<Node>()
        q.offer(Node(x, y))
        visited[x][y] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            popList.add(cur)
            for (i in 0 until 4) {
                val nx = cur.x + d[i].x
                val ny = cur.y + d[i].y
                if (nx !in 0 until 12 || ny !in 0 until 6 || visited[nx][ny]) continue
                if (field[nx][ny] == target) {
                    q.offer(Node(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }
        if (popList.size >= 4) {
            for (p in popList) {
                field[p.x][p.y] = '.'
            }
            return true
        }
        return false
    }

    private fun fallDown() {
        for (j in 0 until 6) {
            val q = LinkedList<Char>() as Queue<Char>
            for (i in 11 downTo topRowIdx) {
                if (field[i][j] != '.') {
                    q.offer(field[i][j])
                    field[i][j] = '.'
                }
            }
            var idx = 11
            while (q.isNotEmpty()) {
                field[idx][j] = q.poll()
                idx--
            }
        }
        findTopRowIdx()
    }

    private fun findTopRowIdx() {
        for (i in topRowIdx until 12) {
            for (j in 0 until 6) {
                if (field[i][j] != '.') {
                    topRowIdx = i
                    return
                }
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        field = Array(12) { readLine().toCharArray() }
        while (true) {
            val isPop = check()
            if (isPop) {
                fallDown()
                answer++
            } else break
        }
        println(answer)
    }
}

fun main() {
    `11559`().solution()
}