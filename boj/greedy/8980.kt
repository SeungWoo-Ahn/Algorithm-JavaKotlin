package boj.greedy

import java.util.StringTokenizer

class `8980` {
    private data class Box(val from: Int, val to: Int, val cnt: Int): Comparable<Box> {
        override fun compareTo(other: Box): Int {
            return if (to != other.to) to - other.to
            else from - other.from
        }
    }

    private var limit = 0
    private lateinit var cntList: IntArray
    private lateinit var boxList: Array<Box>

    private fun getMinLeft(from: Int, to: Int): Int {
        var maxCnt = 0
        for (i in from until to) {
            if (cntList[i] > maxCnt) {
                maxCnt = cntList[i]
            }
        }
        return limit - maxCnt
    }

    private fun addCnt(from: Int, to: Int, addedCnt: Int) {
        for (i in from until to) {
            cntList[i] += addedCnt
        }
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val n = st.nextToken().toInt()
        cntList = IntArray(n + 1)
        limit = st.nextToken().toInt()
        val m = readLine().toInt()
        boxList = Array(m) {
            st = StringTokenizer(readLine(), " ")
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            val cnt = st.nextToken().toInt()
            Box(from, to, cnt)
        }.apply { sort() }
    }

    fun solution() {
        input()
        var total = 0
        for ((from, to, cnt) in boxList) {
            val minLeft = getMinLeft(from, to)
            val addedCnt = minOf(cnt, minLeft)
            if (addedCnt == 0) continue
            addCnt(from, to, addedCnt)
            total += addedCnt
        }
        print(total)
    }
}

fun main() {
    `8980`().solution()
}