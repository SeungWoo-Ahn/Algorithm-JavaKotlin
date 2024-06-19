package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `2573` {
    private data class Node(val x: Int, val y: Int)

    private var map: Array<IntArray> = arrayOf()
    private val d = listOf(
        Node(1, 0),
        Node(-1, 0),
        Node(0, 1),
        Node(0, -1)
    )
    private var time = 0

    private fun iceBergMelt(n: Int, m: Int) {
        val meltingMap = Array(n) { IntArray(m) }
        for (x in 1 until n - 1) {
            for (y in 1 until m - 1) {
                if (map[x][y] == 0) continue
                var oceanCnt = 0
                for ((dx, dy) in d) {
                    val nx = x + dx
                    val ny = y + dy
                    if (outOfBoundary(nx, ny)) continue
                    if (map[nx][ny] == 0) oceanCnt++
                }
                meltingMap[x][y] = oceanCnt
            }
        }
        for (x in 1 until n - 1) {
            for (y in 1 until m - 1) {
                if (meltingMap[x][y] == 0 || map[x][y] == 0) continue
                val remainIceBerg = map[x][y] - meltingMap[x][y]
                map[x][y] = if (remainIceBerg > 0) remainIceBerg else 0
            }
        }
        time++
    }

    private fun checkIceBergSplit(n: Int, m: Int): Boolean {
        var iceBergRemain = 0
        val visited = Array(n) { BooleanArray(m) }
        for (x in 1 until n - 1) {
            for (y in 1 until m - 1) {
                if (!visited[x][y] && map[x][y] > 0) {
                    iceBergRemain++
                    bfs(x, y, visited)
                }
            }
        }
        if (iceBergRemain == 0) time = 0
        return iceBergRemain != 1
    }

    private fun bfs(startX: Int, startY: Int, visited: Array<BooleanArray>) {
        val q = LinkedList<Node>() as Queue<Node>
        q.offer(Node(startX, startY))
        visited[startX][startY] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for ((dx, dy) in d) {
                val nx = x + dx
                val ny = y + dy
                if (outOfBoundary(nx, ny)) continue
                if (visited[nx][ny] || map[nx][ny] == 0) continue
                visited[nx][ny] = true
                q.offer(Node(nx, ny))
            }
        }
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[0].indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(m) { st.nextToken().toInt() }
        }
        while (true) {
            iceBergMelt(n, m)
            if (checkIceBergSplit(n, m)) break
        }
        print(time)
    }
}

fun main() {
    `2573`().solution()
}