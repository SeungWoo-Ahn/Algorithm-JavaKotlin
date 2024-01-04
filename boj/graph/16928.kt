package boj.graph

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `16928` {

    data class Node(val pos: Int, val cnt: Int)

    fun solution() = with(System.`in`.bufferedReader()) {
        val board = IntArray(101)
        val (N, M) = readLine().split(" ").map { it.toInt() }
        repeat(N + M) {
            val st = StringTokenizer(readLine())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            board[from] = to
        }
        val visited = BooleanArray(101)
        val q: Queue<Node> = LinkedList<Node>().apply {
            offer(Node(1, 0))
            visited[1] = true
        }
        var answer = 0
        outer@ while (q.isNotEmpty()) {
            val node = q.poll()
            for(i in 1 .. 6) {
                val newPos = if (board[node.pos + i] == 0) node.pos + i else board[node.pos + i]
                if (newPos >= 100) {
                    answer = node.cnt + 1
                    break@outer
                }
                if (visited[newPos]) continue
                q.offer(Node(newPos, node.cnt + 1))
                visited[newPos] = true
            }
        }
        print(answer)
    }
}

fun main() {
    `16928`().solution()
}