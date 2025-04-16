package boj.math

import java.util.StringTokenizer

class `21920` {
    private fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine(), " ")
        val a = IntArray(n) { st.nextToken().toInt() }
        val x = readLine().toInt()
        var sum = 0.0
        var cnt = 0
        for (num in a) {
            if (gcd(x, num) == 1) {
                sum += num
                cnt++
            }
        }
        print(sum / cnt)
    }
}

fun main() {
    `21920`().solution()
}