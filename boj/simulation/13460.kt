package boj.simulation

import java.util.LinkedList
import java.util.Queue

class `13460` {
    data class Node(val x: Int, val y: Int)

    private var board: Array<CharArray> = arrayOf()
    private var red = Node(0, 0)
    private var blue = Node(0, 0)
    private val dist = Array(11) { Array(11) { Array(11) { IntArray(11) { -1 } } } }
    private val d = listOf(Node(0, 1), Node(1, 0), Node(0, -1), Node(-1, 0))

    private fun bfs(): Int {
        val q: Queue<List<Int>> = LinkedList()
        q.offer(listOf(red.x, red.y, blue.x, blue.y))
        dist[red.x][red.y][blue.x][blue.y] = 0

        while (q.isNotEmpty()) {
            val (rx, ry, bx, by) = q.poll()
            val cnt = dist[rx][ry][bx][by]
            if (cnt >= 10) return -1
            for (i in 0 until 4) {
                var (nRx, nRy, nBx, nBy) = listOf(rx, ry, bx, by)

                while (board[nBx + d[i].x][nBy + d[i].y] == '.') {
                    nBx += d[i].x
                    nBy += d[i].y
                }
                if (board[nBx + d[i].x][nBy + d[i].y] == 'O') continue

                while (board[nRx + d[i].x][nRy + d[i].y] == '.') {
                    nRx += d[i].x
                    nRy += d[i].y
                }
                if (board[nRx + d[i].x][nRy + d[i].y] == 'O') return cnt + 1

                if (nRx == nBx && nRy == nBy) {
                    when (i) {
                        0 -> if (ry < by) nRy-- else nBy--
                        1 -> if (rx < bx) nRx-- else nBx--
                        2 -> if (ry > by) nRy++ else nBy++
                        3 -> if (rx > bx) nRx++ else nBx++
                    }
                }

                if (dist[nRx][nRy][nBx][nBy] != -1) continue
                dist[nRx][nRy][nBx][nBy] = cnt + 1
                q.offer(listOf(nRx, nRy, nBx, nBy))
            }
        }
        return -1
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        board = Array(N) { readLine().toCharArray() }
        for (i in 0 until N) {
            for (j in 0 until M) {
                when (board[i][j]) {
                    'R' -> red = Node(i, j).also { board[i][j] = '.' }
                    'B' -> blue = Node(i, j).also { board[i][j] = '.' }
                }
            }
        }
        println(bfs())
    }
}

fun main() {
    `13460`().solution()
}