package boj.graph

import java.util.LinkedList
import java.util.Queue

class `2206` {
    data class Pos(
        val x: Int,
        val y: Int,
        val cnt: Int = 1,
        val wFlag: Int = 0
    )

    private val d = listOf(Pos(1, 0), Pos(-1, 0), Pos(0, 1), Pos(0, -1))

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        val map = Array(N) { readLine().toCharArray().map { it.digitToInt() }.toIntArray() }
        val visited = Array(N) { Array(M) { IntArray(2) { 1000000 } } }
            .apply {
                this[0][0][0] = 0
                this[0][0][1] = 0
            }
        val inRange: (Int, Int) -> Boolean = { x, y -> x in 0 until N && y in 0 until M }
        val q: Queue<Pos> = LinkedList<Pos>().apply { offer(Pos(0, 0)) }
        while (q.isNotEmpty()) {
            val (x, y, cnt, used) = q.poll()
            if (x == N - 1 && y == M - 1) {
                print(cnt)
                return@with
            }
            d.forEach { (dx, dy) ->
                val newX = x + dx
                val newY = y + dy
                if (inRange(newX, newY)) {
                    when(map[newX][newY]) {
                        0 -> {
                            if (cnt + 1 < visited[newX][newY][used]) {
                                visited[newX][newY][used] = cnt + 1
                                q.offer(Pos(newX, newY, cnt + 1, used))
                            }
                        }
                        1 -> {
                            if (used == 0 && cnt + 1 < visited[newX][newY][1]) {
                                visited[newX][newY][1] = cnt + 1
                                q.offer(Pos(newX, newY, cnt + 1, 1))
                            }
                        }
                    }
                }
            }
        }
        print(-1)
    }
}

fun main() {
    `2206`().solution()
}