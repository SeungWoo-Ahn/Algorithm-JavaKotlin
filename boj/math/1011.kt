package boj.math

import kotlin.math.floor
import kotlin.math.sqrt

class `1011` {
    private fun calcMinCost(target: Int): Int {
        val sqrtCost = sqrt(target.toDouble())
        return floor(sqrtCost * 2 - 1e-9).toInt()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        val t = readLine().toInt()
        repeat(t) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            val target = y - x
            sb.appendLine(calcMinCost(target))
        }
        print(sb)
    }
}

fun main() {
    `1011`().solution()
}