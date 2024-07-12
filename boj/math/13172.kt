package boj.math

class `13172` {
    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }

    private fun pow(b: Int, n: Long): Long {
        if (n == 1L) return b.toLong()
        var ret = pow(b, n / 2)
        ret = ret * ret % MOD
        if (n % 2 == 1L) {
            ret = ret * b % MOD
        }
        return ret
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val m = readLine().toInt()
        var result = 0L
        repeat(m) {
            var (n, s) = readLine().split(" ").map { it.toInt() }
            val gcd = gcd(maxOf(n, s), minOf(n, s))
            n /= gcd
            s /= gcd
            result += s * pow(n, MOD - 2) % MOD
            result %= MOD
        }
        print(result)
    }

    companion object {
        private const val MOD = 1_000_000_007L
    }
}

fun main() {
    `13172`().solution()
}