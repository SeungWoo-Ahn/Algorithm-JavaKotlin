package boj.simulation

import java.util.LinkedList
import java.util.Queue

class `15685` {
    data class Node(val x: Int, val y: Int)

    private val map = Array(101) { BooleanArray(101) }
    private val dir = listOf(Node(1, 0), Node(0, -1), Node(-1, 0), Node(0, 1))

    private fun dragonCurve(x: Int, y: Int, d: Int, g: Int) {
        val dirList = arrayListOf<Int>()
        dirList.add(d)
        var generation = 1
        while (generation <= g) {
            val size = dirList.size
            for (i in size - 1 downTo 0) {
                dirList.add((dirList[i] + 1) % 4)
            }
            generation++
        }
        val nodeQ: Queue<Node> = LinkedList()
        nodeQ.offer(Node(x, y))
        var idx = 0
        while (nodeQ.isNotEmpty()) {
            val cur = nodeQ.poll()
            map[cur.x][cur.y] = true
            if (idx == dirList.size) break
            val nx = cur.x + dir[dirList[idx]].x
            val ny = cur.y + dir[dirList[idx]].y
            if (nx in 0 .. 100 && ny in 0 .. 100) {
                nodeQ.offer(Node(nx, ny))
            }
            idx++
        }
    }

    fun solution() = with(System.`in`.bufferedReader()){
        val n = readLine().toInt()
        repeat(n) {
            val (x, y, d, g) = readLine().split(" ").map { it.toInt() }
            dragonCurve(x, y, d, g)
        }
        var cnt = 0
        for (i in 0 until 100) {
            for (j in 0 until 100) {
                if (map[j][i] && map[j][i + 1] && map[j + 1][i] && map[j + 1][i + 1]) cnt++
            }
        }
        println(cnt)
    }
}

fun main() {
    `15685`().solution()
}