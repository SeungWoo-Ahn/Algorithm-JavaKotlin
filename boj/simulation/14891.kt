package boj.simulation

import kotlin.math.pow

class `14891` {
    private var gears: Array<CharArray> = arrayOf()
    private val twelveIdx = IntArray(4)

    private fun check(num: Int, rotateArr: IntArray) {
        for (i in num + 1 until  4) {
            if (leftGear(i) != rightGear(i - 1)) {
                when (rotateArr[i - 1]) {
                    1 -> rotateArr[i] = -1
                    -1 -> rotateArr[i] = 1
                }
            } else break
        }
        for (i in num - 1 downTo 0) {
            if (rightGear(i) != leftGear(i + 1)) {
                when (rotateArr[i + 1]) {
                    1 -> rotateArr[i] = -1
                    -1 -> rotateArr[i] = 1
                }
            } else break
        }
    }

    private fun leftGear(num: Int): Char {
        var idx = twelveIdx[num] - 2
        if (idx < 0) idx += 8
        return gears[num][idx]
    }

    private fun rightGear(num: Int): Char {
        var idx = twelveIdx[num] + 2
        if (idx >= 8) idx -= 8
        return gears[num][idx]
    }

    private fun rotate(rotateArr: IntArray) {
        for (i in 0 until 4) {
            when (rotateArr[i]) {
                1 -> {
                    var idx = twelveIdx[i] - 1
                    if (idx < 0) idx += 8
                    twelveIdx[i] = idx
                }
                -1 -> {
                    var idx = twelveIdx[i] + 1
                    if (idx >= 8) idx -= 8
                    twelveIdx[i] = idx
                }
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        gears = Array(4) { readLine().toCharArray() }
        val k = readLine().toInt()
        repeat(k) {
            val (num, direction) = readLine().split(" ").map { it.toInt() }
            val rotateArr = IntArray(4)
            rotateArr[num - 1] = direction
            check(num - 1, rotateArr)
            rotate(rotateArr)
        }
        var answer = 0
        for (i in 0 .. 3) {
            answer += gears[i][twelveIdx[i]].digitToInt() * 2.0.pow(i).toInt()
        }
        println(answer)
    }
}

fun main() {
    `14891`().solution()
}