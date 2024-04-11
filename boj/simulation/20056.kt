package boj.simulation

import java.util.LinkedList
import java.util.Queue

class `20056` {
    private data class Node(val x: Int, val y: Int)
    private data class FireBall(val m: Int, val s: Int, val d: Int, val pos: Node)

    private var map: Array<Array<Queue<FireBall>>> = arrayOf()
    private val fireBallQ = LinkedList<FireBall>() as Queue<FireBall>
    private val d = listOf(
        Node(-1, 0),
        Node(-1, 1),
        Node(0, 1),
        Node(1, 1),
        Node(1, 0),
        Node(1, -1),
        Node(0, -1),
        Node(-1, -1),
    )

    private fun moveFireBalls(n : Int) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                while (map[i][j].isNotEmpty()) {
                    fireBallQ.offer(map[i][j].poll())
                }
            }
        }
        while (fireBallQ.isNotEmpty()) {
            val fireBall = fireBallQ.poll()
            val (x, y) = fireBall.pos
            val nx = findPos(n, x, d[fireBall.d].x * fireBall.s)
            val ny = findPos(n, y, d[fireBall.d].y * fireBall.s)
            map[nx][ny].offer(fireBall.copy(pos = Node(nx, ny)))
        }
    }

    private fun findPos(n: Int, cur: Int, length: Int): Int {
        var pos = (cur + length) % n
        if (pos < 0) {
            while (pos < 0) pos += n
        }
        return pos
    }

    private fun combineFireBalls(n: Int) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (map[i][j].size >= 2) {
                    combineFireBall(i, j)
                }
            }
        }
    }

    private fun combineFireBall(x: Int, y: Int) {
        val size = map[x][y].size
        var mSum = 0
        var sSum = 0
        var oddCnt = 0
        while (map[x][y].isNotEmpty()) {
            map[x][y].poll().let {
                mSum += it.m
                sSum += it.s
                if (it.d % 2 == 0) oddCnt++
            }
        }
        if (mSum / 5 == 0) return
        var d = if (oddCnt == size || oddCnt == 0) 0 else 1
        repeat(4) {
            map[x][y].offer(FireBall(mSum / 5, sSum / size, d, Node(x, y)))
            d += 2
        }
    }

    private fun calcMassSum(n: Int): Int {
        var sum = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                while (map[i][j].isNotEmpty()) {
                    sum += map[i][j].poll().m
                }
            }
        }
        return sum
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M, K) = splitToInt(readLine())
        map = Array(N) { Array(N) { LinkedList() } }
        repeat(M) {
            val (r, c, m, s, d) = splitToInt(readLine())
            map[r - 1][c - 1].offer(FireBall(m, s, d, Node(r - 1, c - 1)))
        }
        repeat(K) {
            moveFireBalls(N)
            combineFireBalls(N)
        }
        println(calcMassSum(N))
    }

    private fun splitToInt(input: String): List<Int> = input.split(" ").map { it.toInt() }

}

fun main() {
    `20056`().solution()
}