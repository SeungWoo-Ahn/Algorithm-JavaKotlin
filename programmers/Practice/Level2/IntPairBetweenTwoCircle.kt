package programmers.Practice.Level2

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

class IntPairBetweenTwoCircle {
    private fun calcY(r: Int, x: Int): Double {
        val rd = r.toDouble()
        val xd = x.toDouble()
        return sqrt(rd * rd - xd * xd)
    }

    private fun getYFloor(r: Int, x: Int): Int {
        val y = calcY(r, x)
        return floor(y).toInt()
    }

    private fun getYCeil(r: Int, x: Int): Int {
        val y = calcY(r, x)
        return ceil(y).toInt()
    }

    fun solution(r1: Int, r2: Int): Long {
        var cnt = 0L
        for (x in 1..r1) {
            cnt += getYFloor(r2, x) - getYCeil(r1, x) + 1
        }
        for (x in r1 + 1..r2) {
            cnt += getYFloor(r2, x) + 1
        }
        return cnt * 4
    }
}

fun main() {
    val r1 = 2
    val r2 = 3
    val answer = IntPairBetweenTwoCircle().solution(r1, r2)
    print(answer)
}