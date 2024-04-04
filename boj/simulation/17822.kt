package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `17822` {
    private var discs: Array<ArrayDeque<Int>> = arrayOf()
    private val removeQ = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
    private val d = listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
    private var cnt = 0
    private var total = 0

    private fun rotateDiscs(n: Int, m: Int, x: Int, d: Int, k: Int) {
        for (i in x .. n step x) {
            when (d) {
                CLOCKWISE -> {
                    if (k <= m - k) rotateClockwise(i, k)
                    else rotateCounterClockwise(i, m - k)
                }
                COUNTER_CLOCKWISE -> {
                    if (k <= m - k) rotateCounterClockwise(i, k)
                    else rotateClockwise(i, m - k)
                }
            }
        }
    }

    private fun rotateClockwise(num: Int, time: Int) {
        repeat(time) {
            discs[num].addFirst(discs[num].removeLast())
        }
    }

    private fun rotateCounterClockwise(num: Int, time: Int) {
        repeat(time) {
            discs[num].addLast(discs[num].removeFirst())
        }
    }

    private fun findAdjSame(n: Int, m: Int) {
        val visited = Array(n + 1) { BooleanArray(m) }
        for (i in 1 .. n) {
            for (j in 0 until m) {
                if (discs[i][j] != DUMMY && !visited[i][j]) {
                    bfs(n, m, i, j, discs[i][j], visited)
                }
            }
        }
    }

    private fun bfs(n: Int, m: Int, startX: Int, startY: Int, target: Int, visited: Array<BooleanArray>) {
        var adjSameExist = false
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        val startNode = Pair(startX, startY)
        q.offer(startNode)
        visited[startX][startY] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for (i in d.indices) {
                val nx = x + d[i].first
                var ny = y + d[i].second
                if (ny < 0) ny = m - 1
                else if (ny > m - 1) ny = 0
                if (nx !in 1 .. n || visited[nx][ny]) continue
                if (discs[nx][ny] == target) {
                    q.offer(Pair(nx, ny))
                    removeQ.offer(Pair(nx, ny))
                    visited[nx][ny] = true
                    if (!adjSameExist) adjSameExist = true
                }
            }
        }
        if (adjSameExist) removeQ.offer(startNode)
    }

    private fun removeAdjSame() {
        while (removeQ.isNotEmpty()) {
            val pair = removeQ.poll()
            total -= discs[pair.first][pair.second]
            cnt--
            discs[pair.first][pair.second] = DUMMY
        }
    }

    private fun changeNumbersByAvg(n: Int) {
        val avg = total.toFloat() / cnt
        for (i in 0 .. n) {
            for (j in discs[i].indices) {
                val num = discs[i][j]
                if (num == DUMMY) continue
                if (num > avg) {
                    discs[i][j]--
                    total--
                } else if (num < avg) {
                    discs[i][j]++
                    total++
                }
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, t) = readLine().split(" ").map { it.toInt() }
        discs = Array(n + 1) { ArrayDeque() }
        cnt = n * m
        repeat(n) { i ->
            val st = StringTokenizer(readLine())
            repeat(m) {
                st.nextToken().toInt().let { num ->
                    total += num
                    discs[i + 1].addLast(num)
                }
            }
        }
        repeat(t) {
            val (x, d, k) = readLine().split(" ").map { it.toInt() }
            rotateDiscs(n, m, x, d, k)
            findAdjSame(n, m)
            if (removeQ.isNotEmpty()) removeAdjSame()
            else changeNumbersByAvg(n)
        }
        println(total)
    }

    companion object {
        const val CLOCKWISE = 0
        const val COUNTER_CLOCKWISE = 1
        const val DUMMY = 0
    }
}

fun main() {
    `17822`().solution()
}