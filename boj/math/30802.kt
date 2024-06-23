package boj.math

import java.util.StringTokenizer

class `30802` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(6) { st.nextToken().toInt() }
        val (t, p) = readLine().split(" ").map { it.toInt() }
        var shirtCnt = 0
        for (num in arr) {
            shirtCnt += if (num % t == 0) num / t
                        else num / t + 1
        }
        println(shirtCnt)
        println("${n / p} ${n % p}")
    }
}

fun main() {
    `30802`().solution()
}