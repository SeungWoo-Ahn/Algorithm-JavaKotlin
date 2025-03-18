package boj.two_pointer

import java.util.StringTokenizer
import kotlin.math.abs

class `14921` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val a = IntArray(n) { st.nextToken().toInt() }
        if (a.first() >= 0) {
            print(a[0] + a[1])
            return
        }
        if (a.last() <= 0) {
            print(a[n - 1] + a[n - 2])
            return
        }
        var s = 0
        var e = n - 1
        var minB = 200_000_000
        while (s < e) {
            val b = a[s] + a[e]
            if (abs(b) < abs(minB)) {
                minB = b
                if (minB == 0) break
            }
            if (abs(a[s + 1] + a[e]) > abs(a[s] + a[e - 1])) {
                e--
            } else {
                s++
            }
        }
        print(minB)
    }
}

fun main() {
    `14921`().solution()
}