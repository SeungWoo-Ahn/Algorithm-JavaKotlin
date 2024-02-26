package boj.dp

class `11057` {
    private val d = Array(1001) { LongArray(10) }
    private val mod = 10007

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        for (i in 1 .. n) {
            d[i][0] = 1
            for (j in 1 .. 9) {
                d[i][j] = (d[i][j - 1] + d[i - 1][j]) % mod
            }
        }
        println(d[n].sum() % mod)
    }
}

fun main() {
    `11057`().solution()
}