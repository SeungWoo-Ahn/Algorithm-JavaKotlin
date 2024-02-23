package boj.dp

import java.util.StringTokenizer
import kotlin.math.max

class `11055` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        val d = arr.copyOf()
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (arr[j] < arr[i]) d[i] = max(d[i], d[j] + arr[i])
            }
        }
        print(d.max())
    }
}

fun main() {
    `11055`().solution()
}