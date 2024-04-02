package boj.graph

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.min

class `17142` {
    private data class Node(val x: Int, val y: Int, val time: Int = 0)

    private var map: Array<IntArray> = arrayOf()
    private val virusPos = arrayListOf<Node>()
    private var arr = intArrayOf()
    private val d = listOf(
        Node(1, 0),
        Node(-1, 0),
        Node(0, 1),
        Node(0, -1)
    )
    private var answer = Int.MAX_VALUE
    private var empty = 0

    private fun combination(m: Int, depth: Int = 0, startIdx: Int = 0) {
        if (depth == m) {
            spread()
            return
        }
        for (i in startIdx until virusPos.size) {
            arr[depth] = i
            combination(m, depth + 1, i + 1)
        }
    }

    private fun spread() {
        val q = LinkedList<Node>() as Queue<Node>
        val visited = Array(map.size) { BooleanArray(map.size) }
        var cnt = empty
        arr.forEach {
            val node = virusPos[it]
            q.offer(Node(node.x, node.y))
            visited[node.x][node.y] = true
        }
        while (q.isNotEmpty()) {
            val (x, y, time) = q.poll()
            for (i in d.indices) {
                val nx = x + d[i].x
                val ny = y + d[i].y
                if (nx !in map.indices || ny !in map.indices) continue
                if (visited[nx][ny] || map[nx][ny] == 1) continue
                if (map[nx][ny] == 0) cnt--
                if (cnt == 0) {
                    answer = min(answer, time + 1)
                    return
                }
                visited[nx][ny] = true
                q.offer(Node(nx, ny, time + 1))
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        map = Array(n) { x ->
            val st = StringTokenizer(readLine())
            IntArray(n) { y ->
                val num = st.nextToken().toInt()
                when (num) {
                    0 -> empty++
                    2 -> virusPos.add(Node(x, y))
                }
                num
            }
        }
        if (empty == 0) {
            println(0)
            return
        }
        arr = IntArray(m)
        combination(m)
        if (answer == Int.MAX_VALUE) println(-1)
        else println(answer)
    }
}

fun main() {
    `17142`().solution()
}