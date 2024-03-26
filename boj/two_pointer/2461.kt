package boj.two_pointer

import java.util.StringTokenizer
import kotlin.math.min

class `2461` {
    private var arr: Array<IntArray> = arrayOf()
    private var indexArr = intArrayOf()
    private var numArr = intArrayOf()
    private var answer = Int.MAX_VALUE

    private fun calc(m: Int): Boolean {
        var max = 0
        var min = Int.MAX_VALUE
        var minIdx = 0
        for (i in numArr.indices) {
            if (numArr[i] > max) max = numArr[i]
            if (numArr[i] < min) {
                min = numArr[i]
                minIdx = i
            }
        }
        answer = min(answer, max - min)
        indexArr[minIdx]++
        if (indexArr[minIdx] == m) return true
        else {
            numArr[minIdx] = arr[minIdx][indexArr[minIdx]]
            return false
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        arr = Array(n) {
            val st = StringTokenizer(readLine())
            val each = IntArray(m) { st.nextToken().toInt() }
            each.sort()
            each
        }
        indexArr = IntArray(n)
        numArr = IntArray(n)
        for (i in numArr.indices) {
            numArr[i] = arr[i][0]
        }
        while (true) {
            if (calc(m)) break
        }
        println(answer)
    }
}

fun main() {
    `2461`().solution()
}