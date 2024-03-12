package boj.two_pointer

import java.util.StringTokenizer
import kotlin.math.min

class `1806` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, sum) = readLine().split(" ").map { it.toInt() }
        val arr = IntArray(n)
        val st = StringTokenizer(readLine())
        repeat(n) { arr[it] = st.nextToken().toInt() }
        var e = 0
        var min = Int.MAX_VALUE
        var total = arr[0]
        for (s in 0 until n) {
            while (e < n && total < sum) {
                e++
                if (e != n) total += arr[e]
            }
            if (e == n) break
            min = min(min, e - s + 1)
            total -= arr[s]
        }
        if (min == Int.MAX_VALUE) min = 0
        println(min)
    }
}

fun main() {
    `1806`().solution()
}