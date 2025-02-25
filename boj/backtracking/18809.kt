package boj.backtracking

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `18809` {
    private data class Pos(val x: Int, val y: Int)

    private enum class Block {
        Water, White, Green, Red, Flower
    }

    private data class Node(val time: Int, val type: Block)

    private var n = 0
    private var m = 0
    private var g = 0
    private var r = 0
    private var w = 0
    private lateinit var combList: Array<Block>
    private lateinit var garden: Array<Array<Block>>
    private lateinit var state: Array<Array<Node>>
    private val availablePosList = mutableListOf<Pos>()
    private val q = LinkedList<Pos>() as Queue<Pos>
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)
    private var result = 0

    private fun combination(depth: Int, greenCnt: Int, redCnt: Int, whiteCnt: Int) {
        if (greenCnt == g && redCnt == r) {
            setCombResult()
            val flowerCnt = bfs()
            if (flowerCnt > result) {
                result = flowerCnt
            }
            return
        }
        if (greenCnt < g) {
            combList[depth] = Block.Green
            combination(depth + 1, greenCnt + 1, redCnt, whiteCnt)
            combList[depth] = Block.White
        }
        if (redCnt < r) {
            combList[depth] = Block.Red
            combination(depth + 1, greenCnt, redCnt + 1, whiteCnt)
            combList[depth] = Block.White
        }
        if (whiteCnt < w) {
            combList[depth] = Block.White
            combination(depth + 1, greenCnt, redCnt, whiteCnt + 1)
        }
    }

    private fun setCombResult() {
        state = Array(n) { Array(m) { Node(0, Block.White) } }
        for (i in combList.indices) {
            if (combList[i] == Block.Red || combList[i] == Block.Green) {
                val pos = availablePosList[i]
                state[pos.x][pos.y] = Node(0, combList[i])
                q += pos
            }
        }
    }

    private fun bfs(): Int {
        var flowerCnt = 0
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            val (time, type) = state[x][y]
            if (type == Block.Flower) continue
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny) || garden[nx][ny] == Block.Water) continue
                if (state[nx][ny].type == Block.White) {
                    state[nx][ny] = Node(time + 1, type)
                    q += Pos(nx, ny)
                } else if (state[nx][ny].type == Block.Red) {
                    if (type == Block.Green && state[nx][ny].time == time + 1) {
                        flowerCnt++
                        state[nx][ny] = state[nx][ny].copy(type = Block.Flower)
                    }
                } else if (state[nx][ny].type == Block.Green) {
                    if (type == Block.Red && state[nx][ny].time == time + 1) {
                        flowerCnt++
                        state[nx][ny] = state[nx][ny].copy(type = Block.Flower)
                    }
                }
            }
        }
        return flowerCnt
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in garden.indices || y !in garden[x].indices
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        g = st.nextToken().toInt()
        r = st.nextToken().toInt()
        garden = Array(n) { x ->
            st = StringTokenizer(readLine(), " ")
            Array(m) { y ->
                val info = st.nextToken().toInt()
                when (info) {
                    0 -> Block.Water
                    1 -> Block.White
                    2 -> {
                        availablePosList += Pos(x, y)
                        Block.White
                    }
                    else -> throw IllegalArgumentException()
                }
            }
        }
        w = availablePosList.size - (g + r)
        combList = Array(availablePosList.size) { Block.White }
    }

    fun solution() {
        input()
        combination(0, 0, 0, 0)
        print(result)
    }
}

fun main() {
    `18809`().solution()
}