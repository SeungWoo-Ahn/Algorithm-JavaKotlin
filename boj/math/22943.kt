package boj.math

class `22943` {
    private lateinit var isPrime: BooleanArray
    private val primeList = mutableListOf<Int>()
    private var result = 0

    private fun fillIsPrime(k: Int) {
        var size = 1
        repeat(k) { size *= 10 }
        isPrime = BooleanArray(size) { true }
        isPrime[0] = false
        isPrime[1] = false
        for (base in 2 until isPrime.size) {
            if (isPrime[base].not()) continue
            if (base * base > isPrime.lastIndex) break
            for (nxt in base * base until isPrime.size step base) {
                isPrime[nxt] = false
            }
        }
    }

    private fun addPrimes() {
        for (num in isPrime.indices) {
            if (isPrime[num]) {
                primeList += num
            }
        }
    }

    private fun comb(k: Int, m: Int, depth: Int, num: Int, visited: BooleanArray) {
        if (depth == k) {
            if (condition1(num) && condition2(num, m)) {
                result++
            }
            return
        }
        val minNxt = if (depth == 0) 1 else 0
        for (nxt in minNxt until visited.size) {
            if (visited[nxt]) continue
            visited[nxt] = true
            comb(k, m, depth + 1, num * 10 + nxt, visited)
            visited[nxt] = false
        }
    }

    private fun condition1(num: Int): Boolean {
        for (prime in primeList) {
            if (prime + prime >= num) break
            if (isPrime[num - prime]) {
                return true
            }
        }
        return false
    }

    private fun condition2(num: Int, m: Int): Boolean {
        var target = num
        while (target % m == 0) {
            target /= m
        }
        for (prime in primeList) {
            if (prime * prime > target) break
            if (target % prime == 0 && isPrime[target / prime]) {
                return true
            }
        }
        return false
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (k, m) = readLine().split(" ").map { it.toInt() }
        fillIsPrime(k)
        addPrimes()
        val visited = BooleanArray(10)
        comb(k, m, 0, 0, visited)
        print(result)
    }
}

fun main() {
    `22943`().solution()
}