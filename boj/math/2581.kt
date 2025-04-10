package boj.math

class `2581` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val m = readLine().toInt()
        val n = readLine().toInt()
        val isPrime = BooleanArray(n + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        for (base in 2..n) {
            if (isPrime[base].not()) continue
            for (nxt in base * 2..n step base) {
                isPrime[nxt] = false
            }
        }
        var min = -1
        var sum = 0
        for (num in m..n) {
            if (isPrime[num]) {
                if (min < 0) {
                    min = num
                }
                sum += num
            }
        }
        if (min < 0) {
            print(min)
        } else {
            print("$sum\n$min")
        }
    }
}

fun main() {
    `2581`().solution()
}