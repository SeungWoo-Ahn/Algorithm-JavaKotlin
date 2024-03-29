package boj.math

import java.util.StringTokenizer

class `13458` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        val (b, c) = readLine().split(" ").map { it.toInt() }
        var answer = n.toLong()
        for (num in arr) {
            if (num - b > 0) {
                answer += if ((num - b) % c == 0) (num - b) / c
                          else (num - b) / c + 1
            }
        }
        println(answer)
    }
}

fun main() {
    `13458`().solution()
}