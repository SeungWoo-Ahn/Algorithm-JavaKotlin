package boj.simulation

import kotlin.math.min

class `15684` {
    data class Node(val x: Int, val y: Int)

    private var map: Array<BooleanArray> = arrayOf()
    private val canPut = arrayListOf<Node>()
    private val arr = IntArray(3)
    private var minCnt = 4

    private fun findCanPut() {
        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j]) continue
                if (map[0].size == 1) {
                    canPut.add(Node(i, j))
                    continue
                }
                when (j) {
                    0 -> if (!map[i][1]) canPut.add(Node(i, j))
                    map[0].size - 1 -> if (!map[i][j - 1]) canPut.add(Node(i, j))
                    else -> if (!map[i][j - 1] && !map[i][j + 1]) canPut.add(Node(i, j))
                }
            }
        }
    }

    private fun backtracking(depth: Int, startIdx: Int) {
        val gameMap = Array(map.size) { i -> map[i].copyOf() }
        if (canPlay(gameMap, depth)) play(gameMap, depth)
        if (depth == min(3, minCnt)) return

        for (i in startIdx until canPut.size) {
            arr[depth] = i
            backtracking(depth + 1, i + 1)
        }
    }

    private fun canPlay(gameMap: Array<BooleanArray>, depth: Int): Boolean {
        for (i in 0 until depth) {
            gameMap[canPut[arr[i]].x][canPut[arr[i]].y] = true
        }
        if (gameMap[0].size == 1) return true
        for (i in gameMap.indices) {
            for (j in 0 until gameMap[0].size - 1) {
                if (gameMap[i][j] && gameMap[i][j + 1]) return false
            }
        }
        return true
    }

    private fun play(gameMap: Array<BooleanArray>, depth: Int) {
        var isCorrect = true
        for (j in 0 ..  gameMap[0].size) {
            var curCol = j
            for (i in gameMap.indices) {
                when (curCol) {
                    0 -> if (gameMap[i][curCol]) curCol++
                    gameMap[0].size -> if (gameMap[i][curCol - 1]) curCol--
                    else -> {
                        if (gameMap[i][curCol]) curCol++
                        else if (gameMap[i][curCol - 1]) curCol--
                    }
                }
            }
            if (curCol != j) {
                isCorrect = false
                break
            }
        }
        if (isCorrect && depth < minCnt) {
            minCnt = depth
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, h) = readLine().split(" ").map { it.toInt() }
        map = Array(h) { BooleanArray(n - 1) }
        repeat(m) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            map[a - 1][b - 1] = true
        }
        findCanPut()
        backtracking(0, 0)
        if (minCnt == 4) println(-1)
        else println(minCnt)
    }
}

fun main() {
    `15684`().solution()
}