package boj.math

import kotlin.math.max

class `22864` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (a, b, c, m) = readLine().split(" ").map { it.toInt() }
        var fatigue = 0
        var work = 0
        repeat(24) {
            if (fatigue + a <= m) {
                fatigue += a
                work += b
            } else {
                fatigue = max(fatigue - c, 0)
            }
        }
        print(work)
    }
}

fun main() {
    `22864`().solution()
}