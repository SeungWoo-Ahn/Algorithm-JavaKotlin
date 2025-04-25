package boj.greedy

class `11508` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = IntArray(n) { readLine().toInt() }.apply { sortDescending() }
        var cost = 0
        for (i in arr.indices) {
            if ((i + 1) % 3 == 0) continue
            cost += arr[i]
        }
        print(cost)
    }
}

fun main() {
    `11508`().solution()
}