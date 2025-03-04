package boj.simulation

import java.util.StringTokenizer

class `17136` {
    private lateinit var paper: Array<IntArray>
    private var rest = 0
    private var minUsedCnt = USED_CNT_INF

    private fun backtracking(oneCnt: Int, coloredPaper: IntArray, used: Array<BooleanArray>) {
        if (oneCnt == 0) {
            var usedCnt = 5 * 5
            for (size in 1..5) {
                usedCnt -= coloredPaper[size]
            }
            if (usedCnt < minUsedCnt) {
                minUsedCnt = usedCnt
            }
            return
        }
        val (x, y) = findUnusedPos(used)
        for (size in 1..getMaxSize(x, y, used)) {
            if (coloredPaper[size] == 0) continue
            val nextOneCnt = oneCnt - (size * size)
            val nextUsed = getNextUsed(x, y, size, used)
            coloredPaper[size]--
            backtracking(nextOneCnt, coloredPaper, nextUsed)
            coloredPaper[size]++
        }
    }

    private fun findUnusedPos(used: Array<BooleanArray>): Pair<Int, Int> {
        for (x in paper.indices) {
            for (y in paper[x].indices) {
                if (paper[x][y] == 1 && used[x][y].not()) {
                    return x to y
                }
            }
        }
        return 0 to 0
    }

    private fun getMaxSize(sx: Int, sy: Int, used: Array<BooleanArray>): Int {
        for (size in 2..5) {
            val cx = sx + size - 1
            val cy = sy + size - 1
            if (outOfBoundary(cx, cy)) {
                return size - 1
            }
            for (x in cx downTo sx) {
                if (paper[x][cy] != 1 || used[x][cy]) {
                    return size - 1
                }
            }
            for (y in cy downTo sy) {
                if (paper[cx][y] != 1 || used[cx][y]) {
                    return size - 1
                }
            }
        }
        return 5
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in paper.indices || y !in paper[x].indices
    }

    private fun getNextUsed(sx: Int, sy: Int, size: Int, used: Array<BooleanArray>): Array<BooleanArray> {
        val nextUsed = Array(used.size) { used[it].copyOf() }
        for (x in sx..<sx + size) {
            for (y in sy..<sy + size) {
                nextUsed[x][y] = true
            }
        }
        return nextUsed
    }

    private fun cntOnes() {
        for (x in paper.indices) {
            for (y in paper[x].indices) {
                if (paper[x][y] == 1) rest++
            }
        }
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st: StringTokenizer
        paper = Array(10) {
            st = StringTokenizer(readLine(), " ")
            IntArray(10) { st.nextToken().toInt() }
        }
    }

    fun solution() {
        input()
        cntOnes()
        val coloredPaper = IntArray(6) { 5 }
        val used = Array(10) { BooleanArray(10) }
        backtracking(rest, coloredPaper, used)
        if (minUsedCnt == USED_CNT_INF) {
            print(-1)
        } else {
            print(minUsedCnt)
        }
    }

    companion object {
        private const val USED_CNT_INF = 26
    }
}

fun main() {
    `17136`().solution()
}