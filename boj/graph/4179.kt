package boj.graph

import java.util.LinkedList
import java.util.Queue

class `4179` {

    private val d = listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))

    fun solution() = with(System.`in`.bufferedReader()) {
        val (R, C) = readLine().split(" ").map { it.toInt() }

        val fireQ: Queue<Pair<Int, Int>> = LinkedList()
        val dis = Array(R) { IntArray(C) { -1 } }

        val jQ: Queue<Pair<Int, Int>> = LinkedList()
        val jDis = Array(R) { IntArray(C) { -1 } }

        val miro = Array(R) { CharArray(C) }.apply {
            repeat(R) { x ->
                val line = readLine().toCharArray()
                repeat(C) { y ->
                    this[x][y] = line[y]
                    if (this[x][y] == 'J') {
                        jQ.offer(Pair(x, y))
                        jDis[x][y] = 0
                    }
                    if (this[x][y] == 'F') {
                        fireQ.offer(Pair(x, y))
                        dis[x][y] = 0
                    }
                }
            }
        }

        while (fireQ.isNotEmpty()) {
            val (x, y) = fireQ.poll()
            for (i in 0 until 4) {
                val nx = x + d[i].first
                val ny = y + d[i].second
                if (nx !in 0 until R || ny !in 0 until C || miro[nx][ny] == '#' || dis[nx][ny] >= 0) continue
                fireQ.offer(Pair(nx, ny))
                dis[nx][ny] = dis[x][y] + 1
            }
        }

        while (jQ.isNotEmpty()) {
            val (x, y) = jQ.poll()
            for (i in 0 until 4) {
                val nx = x + d[i].first
                val ny = y + d[i].second
                if (nx !in 0 until R || ny !in 0 until C) {
                    print(jDis[x][y] + 1)
                    return@with
                }
                if (miro[nx][ny] == '#' || jDis[nx][ny] >= 0) continue
                if (dis[nx][ny] != -1 && dis[nx][ny] <= jDis[x][y] + 1) continue
                jQ.offer(Pair(nx, ny))
                jDis[nx][ny] = jDis[x][y] + 1
            }
        }

        print("IMPOSSIBLE")
    }
}

fun main() {
    `4179`().solution()
}

