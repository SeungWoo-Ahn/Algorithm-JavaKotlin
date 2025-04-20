package boj.math

class `2168` {
    private fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        val gcd = gcd(x, y)
        val result = (x / gcd + y / gcd - 1) * gcd
        print(result)
    }
}

fun main() {
    `2168`().solution()
}