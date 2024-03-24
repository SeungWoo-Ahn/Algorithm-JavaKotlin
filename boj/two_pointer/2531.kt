package boj.two_pointer

import kotlin.math.max

class `2531` {

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, d, k, c) = readLine().split(" ").map { it.toInt() }
        val belt = IntArray(n) { readLine().toInt() }
        var answer = 0
        var e: Int
        var sort: BooleanArray
        for (s in belt.indices) {
            e = s
            sort = BooleanArray(d + 1)
            var cnt = 0
            repeat(k) {
                if (!sort[belt[e]]) {
                    sort[belt[e]] = true
                    cnt++
                }
                if (e == belt.lastIndex) e = 0
                else e++
            }
            if (!sort[c]) cnt++
            answer = max(answer, cnt)
        }
        println(answer)
    }
}

fun main() {
    `2531`().solution()
}