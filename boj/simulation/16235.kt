package boj.simulation

import java.util.PriorityQueue
import java.util.StringTokenizer

class `16235` {
    private data class Node(val x: Int, val y: Int)

    private var map: Array<Array<PriorityQueue<Int>>> = arrayOf()
    private var power: Array<IntArray> = arrayOf()
    private var a: Array<IntArray> = arrayOf()
    private val d = listOf(
        Node(1, 0),
        Node(-1, 0),
        Node(0, 1),
        Node(0, -1),
        Node(1, 1),
        Node(1, -1),
        Node(-1, 1),
        Node(-1, -1),
    )

    private fun duringSpring(x: Int, y: Int): MutableList<Int> {
        val growList = mutableListOf<Int>()
        val deadList = mutableListOf<Int>()
        while (map[x][y].isNotEmpty()) {
            val age = map[x][y].poll()
            if (age <= power[x][y]) {
                power[x][y] -= age
                growList.add(age + 1)
            } else {
                deadList.add(age)
            }
        }
        growList.forEach { map[x][y].add(it) }
        return deadList
    }

    private fun duringSummer(x: Int, y: Int, deadList: MutableList<Int>) {
        deadList.forEach {
            power[x][y] += it / 2
        }
    }

    private fun duringFall(x: Int, y: Int) {
        val count = map[x][y].count { it % 5 == 0 }
        if (count == 0) return
        for (i in d.indices) {
            val nx = x + d[i].x
            val ny = y + d[i].y
            if (nx !in map.indices || ny !in map[0].indices) continue
            repeat(count) { map[nx][ny].add(1) }
        }
    }

    private fun duringWinter(x: Int, y: Int) {
        power[x][y] += a[x][y]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n ,m, k) = readLine().split(" ").map { it.toInt() }
        map = Array(n) { Array(n) { PriorityQueue() } }
        power = Array(n) { IntArray(n) { 5 } }
        a = Array(n) {
            val st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        repeat(m) {
            val (x, y, z) = readLine().split(" ").map { it.toInt() }
            map[x - 1][y - 1].add(z)
        }
        repeat(k) {
            for (i in map.indices) {
                for (j in map[i].indices) {
                    val deadList = duringSpring(i, j)
                    duringSummer(i, j, deadList)
                }
            }
            for (i in map.indices) {
                for (j in map[i].indices) {
                    duringFall(i, j)
                    duringWinter(i, j)
                }
            }
        }
        var answer = 0
        for (i in map.indices) {
            for (j in map[i].indices) {
                answer += map[i][j].size
            }
        }
        println(answer)
    }
}

fun main() {
    `16235`().solution()
}