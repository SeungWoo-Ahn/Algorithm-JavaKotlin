package boj.backtracking

import java.util.StringTokenizer

class `17825` {
    private data class Node(val num: Int, val score: Int, val redNxt: Int, val blueNxt: Int? = null)

    private val board = Array(33) { i -> Node(i, i * 2, i + 1) }
    private var dices = intArrayOf()
    private var answer = 0

    private fun makeBoard() {
        board[5] = board[5].copy(blueNxt = 22)
        board[10] = board[10].copy(blueNxt = 25)
        board[15] = board[15].copy(blueNxt = 27)
        board[END] = board[END].copy(score = 0)

        board[22] = board[22].copy(score = 13)
        board[23] = board[23].copy(score = 16)
        board[24] = board[24].copy(score = 19, redNxt = 30)

        board[25] = board[25].copy(score = 22)
        board[26] = board[26].copy(score = 24, redNxt = 30)

        board[27] = board[27].copy(score = 28)
        board[28] = board[28].copy(score = 27)
        board[29] = board[29].copy(score = 26, redNxt = 30)

        board[30] = board[30].copy(score = 25)

        board[31] = board[31].copy(score = 30)
        board[32] = board[32].copy(score = 35, redNxt = 20)
    }

    private fun backtracking(pos: IntArray, depth: Int = 0, score: Int = 0) {
        if (depth == 10) {
            if (score > answer) {
                answer = score
            }
            return
        }
        for (i in 0 until 4) {
            val curPos = pos[i]
            if (curPos == END) continue
            val newPos = findNewPos(curPos, dices[depth], pos)
            if (newPos == curPos) continue
            pos[i] = newPos
            backtracking(pos, depth + 1, score + board[newPos].score)
            pos[i] = curPos
        }
    }

    private fun findNewPos(curPos: Int, time: Int, pos: IntArray): Int {
        var newPos = curPos
        if (board[newPos].blueNxt != null) {
            newPos = board[newPos].blueNxt!!
            repeat(time - 1) {
                newPos = board[newPos].redNxt
                if (newPos == END) return END
            }
        } else {
            repeat(time) {
                newPos = board[newPos].redNxt
                if (newPos == END) return END
            }
        }
        return if (newPos != END && pos.contains(newPos)) curPos else newPos
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine())
        dices = IntArray(10) { st.nextToken().toInt() }
        makeBoard()
        val startPositions = intArrayOf(START, START, START, START)
        backtracking(startPositions)
        println(answer)
    }

    companion object {
        const val START = 0
        const val END = 21
    }
}

fun main() {
    `17825`().solution()
}