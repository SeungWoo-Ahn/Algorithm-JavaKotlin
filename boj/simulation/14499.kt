package boj.simulation

import java.lang.StringBuilder
import java.util.StringTokenizer

class `14499` {
    private var map: Array<IntArray> = arrayOf()
    private var curX = 0
    private var curY = 0
    private val dice = IntArray(6)
    private val d = listOf(listOf(), listOf(0, 1), listOf(0, -1), listOf(-1, 0), listOf(1, 0))
    private val sb = StringBuilder()

    private fun move(direction: Int) {
        curX += d[direction][0]
        curY += d[direction][1]
        if (curX !in map.indices || curY !in map[0].indices) {
            curX -= d[direction][0]
            curY -= d[direction][1]
            return
        }
        when (direction) {
            1 -> {
                dice[0] = dice[3].also { dice[3] = dice[0] }
                dice[2] = dice[5].also { dice[5] = dice[2] }
                dice[2] = dice[3].also { dice[3] = dice[2] }
            }
            2 -> {
                dice[0] = dice[2].also { dice[2] = dice[0] }
                dice[3] = dice[5].also { dice[5] = dice[3] }
                dice[2] = dice[3].also { dice[3] = dice[2] }
            }
            3 -> {
                dice[0] = dice[1].also { dice[1] = dice[0] }
                dice[4] = dice[5].also { dice[5] = dice[4] }
                dice[0] = dice[5].also { dice[5] = dice[0] }
            }
            else -> {
                dice[0] = dice[1].also { dice[1] = dice[0] }
                dice[4] = dice[5].also { dice[5] = dice[4] }
                dice[1] = dice[4].also { dice[4] = dice[1] }
            }
        }
        if (map[curX][curY] == 0) {
            map[curX][curY] = dice[5]
        } else {
            dice[5] = map[curX][curY].also { map[curX][curY] = 0 }
        }
        sb.append("${dice[0]}\n")
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M, x, y, K) = readLine().split(" ").map { it.toInt() }
        curX = x
        curY = y
        map = Array(N) {
            val st = StringTokenizer(readLine())
            IntArray(M) { st.nextToken().toInt() }
        }
        val st = StringTokenizer(readLine())
        repeat(K) { move(st.nextToken().toInt()) }
        println(sb)
    }

}

fun main() {
    `14499`().solution()
}