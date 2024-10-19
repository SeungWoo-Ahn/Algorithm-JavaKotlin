package programmers.Practice.Level2

import kotlin.math.sqrt

class Dotting {
    private fun calcY(d: Int, x: Int): Long {
        val ld = d.toLong()
        val lx = x.toLong()
        val powMinus = (ld * ld - lx * lx).toDouble()
        return sqrt(powMinus).toLong()
    }

    fun solution(k: Int, d: Int): Long {
        var result = 0L
        for (x in 0..d step k) {
            val y = calcY(d, x)
            result += y / k + 1
        }
        return result
    }
}

fun main() {
    val k = 1
    val d = 5
    val answer = Dotting().solution(k, d)
    print(answer)
}