package boj.simulation

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import java.util.StringTokenizer

class `16236` {
    private data class Node(val x: Int, val y: Int, val dis: Int = 0)
    private data class Shark(var x: Int = 0, var y: Int = 0, var size: Int = 2, var eat: Int = 0)

    private var map: Array<IntArray> = arrayOf()
    private val shark = Shark()
    private val d = listOf(Node(-1, 0), Node(0, -1), Node(0, 1), Node(1, 0))
    private var time = 0

    private fun bfs(visited: Array<BooleanArray> = Array(map.size) { BooleanArray(map.size) }): Boolean {
        val pq = PriorityQueue<Node>() { o1, o2 ->
            if (o1.dis != o2.dis) o1.dis - o2.dis
            else if (o1.x != o2.x) o1.x - o2.x
            else o1.y - o2.y
        }
        val q = LinkedList<Node>() as Queue<Node>
        q.offer(Node(shark.x, shark.y))
        visited[shark.x][shark.y] = true
        while (q.isNotEmpty()) {
            val (x, y, dis) = q.poll()
            if (map[x][y] in 1 until shark.size) {
                pq.add(Node(x, y, dis))
            }
            for (i in d.indices) {
                val nx = x + d[i].x
                val ny = y + d[i].y
                if (nx !in map.indices || ny !in map.indices || map[nx][ny] > shark.size || visited[nx][ny]) continue
                visited[nx][ny] = true
                q.offer(Node(nx, ny, dis + 1))
            }
        }
        if (pq.isNotEmpty()) {
            eat(pq.poll())
            return true
        }
        return false
    }

    private fun eat(node: Node) {
        map[node.x][node.y] = 0
        time += node.dis
        shark.x = node.x.also { shark.y = node.y }
        shark.eat++
        if (shark.eat == shark.size) {
            shark.size++
            shark.eat = 0
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        map = Array(n) { x ->
            st = StringTokenizer(readLine())
            IntArray(n) { y ->
                var num = st.nextToken().toInt()
                if (num == 9) {
                    shark.x = x
                    shark.y = y
                    num = 0
                }
                num
            }
        }
        while (true) { if (!bfs()) break }
        println(time)
    }
}

fun main() {
    `16236`().solution()
}