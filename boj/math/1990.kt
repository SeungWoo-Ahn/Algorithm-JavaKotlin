package boj.math

class `1990` {
    private fun getIsPrime(b: Int): BooleanArray {
        val isPrime = BooleanArray(b + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        for (base in 2..b) {
            if (base * base > b) break
            if (isPrime[base].not()) continue
            for (nxt in base * base..b step base) {
                isPrime[nxt] = false
            }
        }
        return isPrime
    }

    private fun isPall(num: Int): Boolean {
        val numStr = num.toString()
        val len = numStr.length
        val mid = len / 2
        var left = if (len % 2 == 0) mid - 1 else mid
        var right = mid
        while (left >= 0) {
            if (numStr[left] != numStr[right]) {
                return false
            }
            left--
            right++
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        val isPrime = getIsPrime(b)
        val sb = StringBuilder()
        for (num in a..b) {
            if (isPrime[num] && isPall(num)) {
                sb.appendLine(num)
            }
        }
        sb.append(-1)
        print(sb)
    }
}

fun main() {
    `1990`().solution()
}