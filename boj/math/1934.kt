package boj.math

import java.util.StringTokenizer

class `1934` {
    private fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        var st: StringTokenizer
        repeat(t) {
            st = StringTokenizer(readLine(), " ")
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            val gcd = gcd(a, b)
            val lcm = a / gcd * b
            sb.appendLine(lcm)
        }
        print(sb)
    }
}

fun main() {
    `1934`().solution()
}