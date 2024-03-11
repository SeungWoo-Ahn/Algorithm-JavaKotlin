package boj.two_pointer

import kotlin.math.min

class `2230` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val arr = IntArray(n)
        repeat(n) { arr[it] = readLine().toInt() }
        arr.sort()
        var e = 0
        var answer = Int.MAX_VALUE
        for (s in 0 until n) {
            while (e < n && arr[e] - arr[s] < m) e++
            if (e == n) break
            answer = min(answer, arr[e] - arr[s])
        }
        println(answer)
    }
}

fun main() {
    `2230`().solution()
}