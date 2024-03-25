package boj.two_pointer

import java.util.StringTokenizer
import kotlin.math.max

class `20922` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        val count = IntArray(100_001)
        var answer = 0
        var e = 0
        count[arr[e]]++
        for (s in arr.indices) {
            while (e + 1 != n && count[arr[e + 1]] < k) {
                e++
                count[arr[e]]++
            }
            answer = max(answer, e - s + 1)
            count[arr[s]]--
        }
        println(answer)
    }
}

fun main() {
    `20922`().solution()
}