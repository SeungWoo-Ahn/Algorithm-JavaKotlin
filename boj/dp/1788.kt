package boj.dp

import kotlin.math.abs

class `1788` {
    private val range = 1_000_000
    private val mod = 1_000_000_000

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val d = IntArray(range * 2 + 1)
        d[range] = 0
        d[range + 1] = 1
        if (n > 0) {
            for (i in range + 2 .. range + n) {
                d[i] = (d[i - 1] + d[i - 2]) % mod
            }
        } else {
            for (i in range - 1 downTo range + n) {
                d[i] = (d[i + 2] - d[i + 1]) % mod
            }
        }
        val answer = d[range + n]
        val sign = if (answer > 0) 1
        else if (answer < 0) -1
        else 0
        println("$sign\n${abs(answer)}")
    }
}

fun main() {
    `1788`().solution()
}