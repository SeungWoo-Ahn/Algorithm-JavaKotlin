package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.max

class `20058` {
    private data class Node(val x: Int, val y: Int)

    private var map: Array<IntArray> = arrayOf()
    private val multipleOfTwo = intArrayOf(1, 2, 4, 8, 16, 32, 64)
    private val d = listOf(Node(1, 0), Node(-1, 0), Node(0, 1), Node(0, -1))
    private var sum = 0

    private fun rotateMap(level: Int) {
        if (level == 0) return
        val size = multipleOfTwo[level]
        for (startX in map.indices step size) {
            for (startY in map.indices step size) {
                rotatePartMap(startX, startY, size)
            }
        }
    }

    private fun rotatePartMap(startX: Int, startY: Int, size: Int) {
        val partMap = Array(size) { IntArray(size) }
        for (x in partMap.indices) {
            for (y in partMap.indices) {
                partMap[x][y] = map[x + startX][y + startY]
            }
        }
        val rotatedPartMap = Array(size) { x ->
            IntArray(size) { y ->
                partMap[partMap.size - y - 1][x]
            }
        }
        for (x in rotatedPartMap.indices) {
            for (y in rotatedPartMap.indices) {
                map[x + startX][y + startY] = rotatedPartMap[x][y]
            }
        }
    }

    private fun decreaseIce() {
        val newMap = Array(map.size) { IntArray(map.size) }
        for (x in map.indices) {
            for (y in map.indices) {
                val iceAmount = map[x][y]
                if (iceAmount == 0) continue
                if (countAdjIce(x, y) >= 3) {
                    newMap[x][y] = iceAmount
                } else {
                    newMap[x][y] = iceAmount - 1
                }
            }
        }
        map = newMap
    }

    private fun countAdjIce(x: Int, y: Int): Int {
        var cnt = 0
        for (i in d.indices) {
            val nx = x + d[i].x
            val ny = y + d[i].y
            if (nx !in map.indices || ny !in map.indices) continue
            if (map[nx][ny] > 0) cnt++
        }
        return cnt
    }

    private fun findBiggestIce(): Int {
        var maxSize = 0
        val visited = Array(map.size) { BooleanArray(map.size) }
        for (x in map.indices) {
            for (y in map.indices) {
                if (visited[x][y] || map[x][y] == 0) continue
                val size = bfs(x, y, visited)
                maxSize = max(maxSize, size)
            }
        }
        return maxSize
    }

    private fun bfs(startX: Int, startY: Int, visited: Array<BooleanArray>): Int {
        val q = LinkedList<Node>() as Queue<Node>
        q.offer(Node(startX, startY))
        visited[startX][startY] = true

        var size = 0
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            size++
            sum += map[x][y]
            for (i in d.indices) {
                val nx = x + d[i].x
                val ny = y + d[i].y
                if (nx !in map.indices || ny !in map.indices || visited[nx][ny]) continue
                if (map[nx][ny] > 0) {
                    q.offer(Node(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }
        return size
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, q) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        map = Array(multipleOfTwo[n]) {
            st = StringTokenizer(readLine())
            IntArray(multipleOfTwo[n]) { st.nextToken().toInt() }
        }
        st = StringTokenizer(readLine())
        repeat(q) {
            val level = st.nextToken().toInt()
            rotateMap(level)
            decreaseIce()
        }
        val biggestIce = findBiggestIce()
        println("$sum\n$biggestIce")
    }
}

fun main() {
    `20058`().solution()
}