package boj.greedy

import java.util.StringTokenizer

class `20300` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine(), " ")
        val arr = LongArray(n) { st.nextToken().toLong() }.apply { sort() }
        var s = 0
        var e = if (n % 2 == 0) n - 1 else n - 2
        var m = 0L
        while (s < e) {
            val sum = arr[s] + arr[e]
            if (sum > m) {
                m = sum
            }
            s++
            e--
        }
        print(m)

    }
}

fun main() {
    `20300`().solution()
}