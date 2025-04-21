package boj.math

class `2436` {
    private fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    private fun getMinSumPair(num: Int): Pair<Int, Int> {
        var first = 0
        var second = 0
        for (i in 1..num) {
            if (i * i > num) break
            if (num % i == 0) {
                val b = num / i
                if (gcd(i, b) == 1) {
                    first = i
                    second = b
                }
            }
        }
        return first to second
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (gcd, lcm) = readLine().split(" ").map { it.toInt() }
        val target = lcm / gcd
        val (first, second) = getMinSumPair(target)
        print("${first * gcd} ${second * gcd}")
    }
}

fun main() {
    `2436`().solution()
}