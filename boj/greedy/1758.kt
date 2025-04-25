package boj.greedy

class `1758` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = IntArray(n) { readLine().toInt() }.apply { sortDescending() }
        var tip = 0L
        for (seq in 1..n) {
            if (arr[seq - 1] <= seq - 1) break
            tip += arr[seq - 1] - (seq - 1)
        }
        print(tip)
    }
}

fun main() {
    `1758`().solution()
}