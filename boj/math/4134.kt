package boj.math

class `4134` {
    private fun isPrime(n: Long): Boolean {
        if (n < 2) return false
        for (i in 2..n) {
            if (i * i > n) break
            if (n % i == 0L) return false
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            var n = readLine().toLong()
            while (isPrime(n).not()) n++
            sb.appendLine(n)
        }
        print(sb)
    }
}

fun main() {
    `4134`().solution()
}