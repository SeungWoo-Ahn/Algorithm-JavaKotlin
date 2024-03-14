package boj.two_pointer

import java.util.StringTokenizer
import kotlin.math.max

class `22862` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        var cnt = 0
        var e = 0
        var answer = 0
        if (arr[0] % 2 == 1) cnt++
        for (s in arr.indices) {
            while (e < n - 1 && arr[e + 1] % 2 + cnt <= k) {
                e++
                cnt += arr[e] % 2
            }
            answer = max(answer, e - s + 1 - cnt)
            cnt -= arr[s] % 2
        }
        println(answer)
    }
}

fun main() {
    `22862`().solution()
}