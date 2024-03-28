package boj.two_pointer

class `2283` {
    private val MAX_VALUE = 1_000_000

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val arr = IntArray(MAX_VALUE + 2)
        repeat(n) {
            val (s, e) = readLine().split(" ").map { it.toInt() }
            for (i in s until e) arr[i]++
        }
        var e = 0
        var sum = arr[0]
        for (s in 0 until MAX_VALUE) {
            while (e < MAX_VALUE && sum < k) {
                e++
                if (sum != MAX_VALUE) {
                    sum += arr[e]
                }
            }
            if (sum == MAX_VALUE) break
            if (sum == k) {
                println("$s ${e + 1}")
                return
            }
            sum -= arr[s]
        }
        println("0 0")
    }
}

fun main() {
    `2283`().solution()
}