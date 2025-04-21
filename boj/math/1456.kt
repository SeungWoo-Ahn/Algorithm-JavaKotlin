package boj.math

import kotlin.math.sqrt

class `1456` {
    private fun getIsNotPrime(size: Int): BooleanArray {
        val isNotPrime = BooleanArray(size + 1)
        isNotPrime[0] = true
        isNotPrime[1] = true
        for (base in 2..size) {
            if (base * base > size) break
            if (isNotPrime[base]) continue
            for (nxt in base * base..size step base) {
                isNotPrime[nxt] = true
            }
        }
        return isNotPrime
    }

    private fun getMaxNum(b: Long): Int {
        return sqrt(b.toDouble()).toInt()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (a, b) = readLine().split(" ").map { it.toLong() }
        val maxNum = getMaxNum(b)
        val isNotPrime = getIsNotPrime(maxNum)
        var cnt = 0
        for (num in 1..maxNum) {
            if (isNotPrime[num]) continue
            var cur = num.toLong() * num
            while (cur <= b) {
                if (cur >= a) {
                    cnt++
                }
                try {
                    cur = Math.multiplyExact(cur, num.toLong())
                } catch (e: ArithmeticException) {
                    break
                }
            }
        }
        print(cnt)
    }
}

fun main() {
    `1456`().solution()
}