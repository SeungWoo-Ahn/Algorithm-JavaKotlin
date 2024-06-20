package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `2146` {
    private data class Node(val x: Int, val y: Int)
    private data class Land(val pos: Node, val dis: Int)

    private var map: Array<IntArray> = arrayOf()
    private var adjOceanCnt: Array<IntArray> = arrayOf()
    private val d = listOf(
        Node(1, 0),
        Node(-1, 0),
        Node(0, 1),
        Node(0, -1),
    )

    private fun numberingLands(n: Int) {
        val visited = Array(n) { BooleanArray(n) }
        adjOceanCnt = Array(n) { IntArray(n) }
        var landNum = 1
        for (x in map.indices) {
            for (y in map.indices) {
                if (visited[x][y] || map[x][y] == 0) continue
                bfs(x, y, landNum, visited)
                landNum++
            }
        }
    }

    private fun bfs(sX: Int, sY: Int, landNum: Int, visited: Array<BooleanArray>) {
        val q = LinkedList<Node>() as Queue<Node>
        q.offer(Node(sX, sY))
        visited[sX][sY] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            var oceanCnt = 0
            for ((dx, dy) in d) {
                val nx = x + dx
                val ny = y + dy
                if (outOfBoundary(nx, ny) || visited[nx][ny]) continue
                if (map[nx][ny] == 0) {
                    oceanCnt++
                    continue
                }
                visited[nx][ny] = true
                q.offer(Node(nx, ny))
            }
            map[x][y] = landNum
            adjOceanCnt[x][y] = oceanCnt
        }
    }

    private fun findShortestBridge(): Int {
        var shortest = 200
        for (x in map.indices) {
            for (y in map.indices) {
                if (map[x][y] == 0 || adjOceanCnt[x][y] < 2) continue
                shortest = minOf(shortest, findShortestDistance(x, y))
            }
        }
        return shortest
    }

    private fun findShortestDistance(sX: Int, sY: Int): Int {
        val landNum = map[sX][sY]
        val q = LinkedList<Land>() as Queue<Land>
        val visited = Array(map.size) { BooleanArray(map.size) }
        q.offer(Land(Node(sX, sY), 0))
        visited[sX][sY] = true
        while (q.isNotEmpty()) {
            val (pos, dis) = q.poll()
            for ((dx, dy) in d) {
                val nx = pos.x + dx
                val ny = pos.y + dy
                if (outOfBoundary(nx, ny) || visited[nx][ny]) continue
                if (map[nx][ny] == 0) {
                    visited[nx][ny] = true
                    q.offer(Land(Node(nx, ny), dis + 1))
                } else if (map[nx][ny] != landNum) {
                    return dis
                }
            }
        }
        return 0
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map.indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        numberingLands(n)
        print(findShortestBridge())
    }
}

fun main() {
    `2146`().solution()
}