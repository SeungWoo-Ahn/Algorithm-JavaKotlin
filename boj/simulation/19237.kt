package boj.simulation

import java.util.Collections
import java.util.PriorityQueue
import java.util.StringTokenizer

class `19237` {
    private data class Node(var x: Int, var y: Int)
    private data class Shark(val num: Int, var dir: Int, val pos: Node, var priority: List<List<Int>> = listOf())
    private data class Smell(val sharkNum: Int, val removeTime: Int)

    private var map: Array<Array<PriorityQueue<Int>>> = arrayOf()
    private var smellMap: Array<Array<Smell?>> = arrayOf()
    private var sharks: Array<Shark?> = arrayOf()
    private val d = listOf(
        Node(-1, 0),
        Node(1, 0),
        Node(0, -1),
        Node(0, 1)
    )
    private var cnt = 0
    private var time = 0

    private fun removeAllSmell() {
        for (i in smellMap.indices) {
            for (j in smellMap[i].indices) {
                val smell = smellMap[i][j] ?: continue
                if (smell.removeTime == time) {
                    smellMap[i][j] = null
                }
            }
        }
    }

    private fun moveSharks() {
        for (num in 1 until sharks.size) {
            val shark = sharks[num] ?: continue
            val newDir = findDirection(shark)
            if (newDir != null) {
                moveShark(shark, newDir - 1)
            }
        }
    }

    private fun findDirection(shark: Shark): Int? {
        val dir = shark.dir
        for (i in shark.priority[dir]) {
            val nx = shark.pos.x + d[i - 1].x
            val ny = shark.pos.y + d[i - 1].y
            if (outOfBoundary(nx, ny) || smellMap[nx][ny] != null) continue
            return i
        }
        for (i in shark.priority[dir]) {
            val nx = shark.pos.x + d[i - 1].x
            val ny = shark.pos.y + d[i - 1].y
            if (outOfBoundary(nx, ny)) continue
            if (smellMap[nx][ny]!!.sharkNum == shark.num)
                return i
        }
        return null
    }

    private fun moveShark(shark: Shark, dir: Int) {
        map[shark.pos.x][shark.pos.y].remove(shark.num)
        val nx = shark.pos.x + d[dir].x
        val ny = shark.pos.y + d[dir].y
        map[nx][ny].add(shark.num)
        shark.pos.x = nx
        shark.pos.y = ny
        shark.dir = dir
    }

    private fun removeSharks() {
        for (num in 1 until sharks.size) {
            val shark = sharks[num] ?: continue
            while (map[shark.pos.x][shark.pos.y].size > 1) {
                val removedNum = map[shark.pos.x][shark.pos.y].poll()
                sharks[removedNum] = null
                cnt--
            }
        }
    }

    private fun leaveSmell(k: Int) {
        for (num in 1 until sharks.size) {
            val shark = sharks[num] ?: continue
            smellMap[shark.pos.x][shark.pos.y] = Smell(shark.num, time + k)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = splitToInt(readLine())
        map = Array(n) { Array(n) { PriorityQueue(Collections.reverseOrder()) } }
        smellMap = Array(n) { Array(n) { null } }
        sharks = Array(m + 1) { null }
        cnt = m
        var st: StringTokenizer
        repeat(n) { x ->
            st = StringTokenizer(readLine())
            repeat(n) { y ->
                val num = st.nextToken().toInt()
                if (num != 0) {
                    map[x][y].add(num)
                    smellMap[x][y] = Smell(num, k)
                    sharks[num] = Shark(num, 0, Node(x, y))
                }
            }
        }
        st = StringTokenizer(readLine())
        repeat(m) { i -> sharks[i + 1]?.dir = st.nextToken().toInt() - 1 }
        repeat(m) { i -> sharks[i + 1]?.priority = List(4) { splitToInt(readLine()) } }
        while (cnt > 1) {
            time++
            moveSharks()
            removeSharks()
            leaveSmell(k)
            removeAllSmell()
            if (time > 1000) {
                time = -1
                break
            }
        }
        println(time)
    }

    private fun splitToInt(input: String): List<Int> = input.split(" ").map { it.toInt() }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map.indices
    }
}

fun main() {
    `19237`().solution()
}