package boj.binary_search

class `1654` {
    private val arr = IntArray(10001)

    private fun isSolved(k: Int, n: Int, x: Long): Boolean {
        var cur: Long = 0
        repeat(k) { cur += arr[it] / x }
        return cur >= n
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (k, n) = readLine().split(" ").map { it.toInt() }
        repeat(k) { arr[it] = readLine().toInt() }
        var s: Long = 1
        var e: Long = 0x7fffffff // 2^31 - 1
        while (s < e) {
            val mid = (s + e + 1) / 2
            if (isSolved(k, n, mid)) s = mid
            else e = mid - 1
        }
        println(s)
    }
}

fun main() {
    `1654`().solution()
}