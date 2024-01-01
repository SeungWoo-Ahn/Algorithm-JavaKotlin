package boj.graph

import java.util.LinkedList
import java.util.Queue

class `2606` {

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val V = readLine().toInt()
        val visited = BooleanArray(N + 1) { false }
        val network = Array(N + 1) { BooleanArray(N + 1) { false } }.apply {
            repeat(V) {
                val (x, y) = readLine().split(" ").map { it.toInt() }
                this[x][y] = true
                this[y][x] = true
            }
        }
        var answer = 0
        val q: Queue<Int> = LinkedList<Int>().apply {
            offer(1)
            visited[1] = true
        }
        while (!q.isEmpty()) {
            val node = q.poll()
            for(i in 1..N) {
                if (network[node][i] && !visited[i]) {
                    q.offer(i)
                    visited[i] = true
                    answer++
                }
            }
        }
        println(answer)
    }
}

fun main() {
    `2606`().solution()
}