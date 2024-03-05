package boj.math

class `11051` {
    private val d = Array(1001) { IntArray(1001) }
    private val mod = 10007

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        for (i in 1 .. n) {
            d[i][0] = 1.also { d[i][i] = 1 }
            for (j in 1 until  i) {
                d[i][j] = (d[i - 1][j] + d[i - 1][j - 1]) % mod
            }
        }
        println(d[n][k])
    }
}

fun main() {
    `11051`().solution()
}