package boj.two_pointer

import java.util.StringTokenizer
import kotlin.math.abs

class `2473` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val value = LongArray(n) { st.nextToken().toLong() }
        value.sort()
        var mid: Int
        var e: Int
        var first = 0L
        var second = 0L
        var third = 0L
        var min = 3_000_000_001L
        for (s in 0 until n - 2) {
            mid = s + 1
            e = n - 1
            while (mid < e) {
                val sum = value[s] + value[mid] + value[e]
                if (abs(sum) < min) {
                    min = abs(sum)
                    first = value[s]
                    second = value[mid]
                    third = value[e]
                }
                if (sum < 0) {
                    mid++
                } else {
                    e--
                }
            }
        }
        print("$first $second $third")
    }
}

fun main() {
    `2473`().solution()
}