package boj.simulation

import java.util.StringTokenizer

class `20055` {
    private var belts = intArrayOf()
    private var robots = booleanArrayOf()
    private var startIdx = 0
    private var zeroCnt = 0
    private var step = 0

    private fun rotateBelts(n: Int) {
        startIdx = findPreIdx(startIdx, n)
        getOffRobotOnLastBelt(n)
    }

    private fun moveRobots(n: Int) {
        var idx = findPreIdx(findEndIdx(n), n)
        while (idx != startIdx) {
            val nxtIdx = findNxtIdx(idx, n)
            if (robots[idx] && !robots[nxtIdx] && belts[nxtIdx] > 0) {
                robots[idx] = false
                robots[nxtIdx] = true
                belts[nxtIdx]--
                if (belts[nxtIdx] == 0) zeroCnt++
            }
            idx = findPreIdx(idx, n)
        }
        getOffRobotOnLastBelt(n)
    }

    private fun putRobotOnFirstBelt() {
        if (belts[startIdx] > 0) {
            robots[startIdx] = true
            belts[startIdx]--
            if (belts[startIdx] == 0) zeroCnt++
        }
    }

    private fun getOffRobotOnLastBelt(n: Int) {
        robots[findEndIdx(n)] = false
    }

    private fun findPreIdx(idx: Int, n: Int): Int {
        return if (idx == 0) 2 * n - 1 else idx - 1
    }

    private fun findNxtIdx(idx: Int, n: Int): Int {
        return (idx + 1) % (2 * n)
    }

    private fun findEndIdx(n: Int): Int {
        return (startIdx + n - 1) % (2 * n)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        belts = IntArray(2 * n) { st.nextToken().toInt() }
        robots = BooleanArray(2 * n)
        while (zeroCnt < k) {
            rotateBelts(n)
            moveRobots(n)
            putRobotOnFirstBelt()
            step++
        }
        println(step)
    }
}

fun main() {
    `20055`().solution()
}