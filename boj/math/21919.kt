package boj.math

import java.util.StringTokenizer

class `21919` {
    private lateinit var isPrime: BooleanArray

    private fun fillIsPrime(max: Int) {
        isPrime = BooleanArray(max + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        for (base in 2..max) {
            if (base * base > max) break
            if (isPrime[base].not()) continue
            for (nxt in base * base..max step base) {
                isPrime[nxt] = false
            }
        }
    }

    private fun getPrimeList(a: IntArray): List<Int> {
        val primeSet = mutableSetOf<Int>()
        for (num in a) {
            if (isPrime[num]) {
                primeSet += num
            }
        }
        return primeSet.toList()
    }

    private fun lcm(primeList: List<Int>): Long {
        var lcm = 1L
        for (prime in primeList) {
            lcm *= prime
        }
        return lcm
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine(), " ")
        val a = IntArray(n) { st.nextToken().toInt() }
        fillIsPrime(a.max())
        val primeList = getPrimeList(a)
        if (primeList.isEmpty()) {
            print(-1)
        } else {
            print(lcm(primeList))
        }
    }
}

fun main() {
    `21919`().solution()
}