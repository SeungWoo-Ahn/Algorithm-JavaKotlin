package boj.backtracking

import java.util.StringTokenizer

class `1182` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, s) = readLine().split(" ").map(String::toInt)
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        var cnt = 0
        for (bit in 1 until (1 shl n)) {
            var sum = 0
            var i = 0
            while (bit shr i > 0) {
                if ((bit shr i) and 1 == 1) {
                    sum += arr[i]
                }
                i++
            }
            if (sum == s) {
                cnt++
            }
        }
        print(cnt)
    }
}

fun main() {
    `1182`().solution()
}