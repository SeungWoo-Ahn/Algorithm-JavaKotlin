package boj.math

class `9421` {
    private fun isSangGeun(num: Int): Boolean {
        var cur = num
        val cache = hashSetOf<Int>()
        while (cur != 1) {
            if (cache.contains(cur)) {
                return false
            }
            cache += cur
            var nxt = 0
            while (cur > 0) {
                val i = cur % 10
                nxt += i * i
                cur /= 10
            }
            cur = nxt
        }
        return true
    }

    private fun getIsPrime(n: Int): BooleanArray {
        val isPrime = BooleanArray(n + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        for (base in 2..n) {
            if (base * base > n) break
            if (isPrime[base].not()) continue
            for (nxt in base * base..n step base) {
                isPrime[nxt] = false
            }
        }
        return isPrime
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val isPrime = getIsPrime(n)
        val sb = StringBuilder()
        for (num in isPrime.indices) {
            if (isPrime[num] && isSangGeun(num)) {
                sb.appendLine(num)
            }
        }
        print(sb)
    }
}

fun main() {
    `9421`().solution()
}