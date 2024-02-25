package boj.dp

import kotlin.math.max

class `2156` {
    private val arr = IntArray(10001)
    private val d = IntArray(10001)

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        for (i in 1 .. n) arr[i] = readLine().toInt()
        d[1] = arr[1]
        d[2] = arr[1] + arr[2]
        for (i in 3 .. n) {
            d[i] = max(d[i - 1], max(d[i - 2] + arr[i], d[i - 3] + arr[i - 1] + arr[i]))
        }
        println(d[n])
    }
}

fun main() {
    `2156`().solution()
}