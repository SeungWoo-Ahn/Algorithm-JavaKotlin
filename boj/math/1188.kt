package boj.math

class `1188` {
    private fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val gcd = gcd(n, m)
        print(m - gcd)
    }
}

fun main() {
    `1188`().solution()
}