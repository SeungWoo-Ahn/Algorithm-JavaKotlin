package programmers.Practice.Level3

import java.util.LinkedList
import java.util.Queue
import kotlin.math.abs

class MazeEscapeCommand {
    private data class Node(val x: Int, val y: Int, val cnt: Int, val commands: String)

    private val dx = intArrayOf(1, 0, 0, -1)
    private val dy = intArrayOf(0, -1, 1, 0)
    private val command = arrayOf('d', 'l', 'r', 'u')

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val shortCut = abs(x - r) + abs(y - c)
        val remain = k - shortCut
        if (remain < 0 || remain % 2 != 0) {
            return "impossible"
        }
        val q = LinkedList<Node>() as Queue<Node>
        val visited = Array(n) { Array(m) { BooleanArray(k + 1) } }
        q += Node(x - 1, y - 1, 0, "")
        visited[x - 1][y - 1][0] = true
        while (q.isNotEmpty()) {
            val (cx, cy, cnt, commands) = q.poll()
            if (cnt == k) {
                if (cx == r - 1 && cy == c - 1) {
                    return commands
                }
                continue
            }
            for (i in 0 until 4) {
                val nx = cx + dx[i]
                val ny = cy + dy[i]
                if (nx !in 0 until n || ny !in 0 until m) continue
                if (visited[nx][ny][cnt + 1]) continue
                visited[nx][ny][cnt + 1] = true
                q += Node(nx, ny, cnt + 1, commands + command[i])
            }
        }
        return "impossible"
    }
}

fun main() {
    val answer = MazeEscapeCommand().solution(3, 4, 2, 3, 3, 1, 5)
    print(answer)
}