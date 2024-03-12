package boj.two_pointer

class `1644` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n == 1) {
            println(0)
            return
        }
        val table = BooleanArray(n + 1) { true }
        for (i in 2 .. n) {
            if (i * i > n) break
            if (!table[i]) continue
            for (j in i * i .. n step i) {
                table[j] = false
            }
        }
        val primeNumbers = mutableListOf<Int>()
        for (i in 2 .. n) {
            if (table[i]) primeNumbers.add(i)
        }
        var e = 0
        var total = primeNumbers[0]
        var answer = 0
        for (s in primeNumbers.indices) {
            while (e < primeNumbers.size && total < n) {
                e++
                if (e != primeNumbers.size) total += primeNumbers[e]
            }
            if (total == n) answer++
            total -= primeNumbers[s]
        }
        println(answer)
    }
}

fun main() {
    `1644`().solution()
}