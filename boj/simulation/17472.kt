package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `17472` {
    private data class Pos(val x: Int, val y: Int)

    private data class Bridge(val from: Int, val to: Int, val cost: Int) : Comparable<Bridge> {
        override fun compareTo(other: Bridge): Int {
            return cost - other.cost
        }
    }

    private var n = 0
    private var m = 0
    private var islandCnt = 0
    private lateinit var map: Array<IntArray>
    private val bridgeList = mutableListOf<Bridge>()
    private lateinit var parent: IntArray
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun find(x: Int): Int {
        if (parent[x] == x) {
            return x
        }
        return find(parent[x]).also { parent[x] = it }
    }

    private fun union(x: Int, y: Int): Boolean {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return false
        if (rootX < rootY) parent[rootY] = rootX
        else parent[rootX] = rootY
        return true
    }

    private fun findMinCost(): Int {
        var totalCost = 0
        var linkedCnt = 0
        parent = IntArray(islandCnt) { it }
        bridgeList.sort()
        for ((from, to, cost) in bridgeList) {
            if (union(from, to).not()) continue
            totalCost += cost
            if (++linkedCnt == islandCnt - 1) break
        }
        return if (linkedCnt != islandCnt - 1) -1
        else totalCost
    }

    private fun findAllAvailableBridge() {
        for (x in map.indices) {
            var from = -1
            var distance = 0
            for (y in map[x].indices) {
                val info = map[x][y]
                if (info < 0) {
                    if (from >= 0) distance++
                } else {
                    if (from < 0) {
                        from = info
                    } else if (info != from) {
                        if (distance >= 2) {
                            bridgeList += Bridge(from, info, distance)
                        }
                        from = info
                        distance = 0
                    } else {
                        distance = 0
                    }
                }
            }
        }
        for (y in map[0].indices) {
            var from = -1
            var distance = 0
            for (x in map.indices) {
                val info = map[x][y]
                if (info < 0) {
                    if (from >= 0) distance++
                } else {
                    if (from < 0) {
                        from = info
                    } else if (info != from) {
                        if (distance >= 2) {
                            bridgeList += Bridge(from, info, distance)
                        }
                        from = info
                        distance = 0
                    } else {
                        distance = 0
                    }
                }
            }
        }
    }

    private fun numberingIslands() {
        val q = LinkedList<Pos>() as Queue<Pos>
        val visited = Array(n) { BooleanArray(m) }
        for (sx in map.indices) {
            for (sy in map[sx].indices) {
                if (visited[sx][sy] || map[sx][sy] < 0) continue
                var minX = n
                var maxX = 0
                var minY = m
                var maxY = 0
                q += Pos(sx, sy)
                visited[sx][sy] = true
                while (q.isNotEmpty()) {
                    val (x, y) = q.poll()
                    minX = minOf(minX, x)
                    maxX = maxOf(maxX, x)
                    minY = minOf(minY, y)
                    maxY = maxOf(maxY, y)
                    map[x][y] = islandCnt
                    for (i in 0 until 4) {
                        val nx = x + dx[i]
                        val ny = y + dy[i]
                        if (outOfBoundary(nx, ny) || visited[nx][ny] || map[nx][ny] < 0) continue
                        q += Pos(nx, ny)
                        visited[nx][ny] = true
                    }
                }
                islandCnt++
            }
        }
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        map = Array(n) {
            st = StringTokenizer(readLine(), " ")
            IntArray(m) { st.nextToken().toInt() - 1 }
        }
    }

    fun solution() {
        input()
        numberingIslands()
        findAllAvailableBridge()
        val minCost = findMinCost()
        print(minCost)
    }
}

fun main() {
    `17472`().solution()
}