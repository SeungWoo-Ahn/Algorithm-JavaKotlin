package boj.dp

class `10844` {
    private val mod = 1_000_000_000

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val d = Array(n + 1) { LongArray(10) }
        for (i in 1 .. 9) d[1][i] = 1
        for (i in 2 .. n) {
            for (j in 0 .. 9) {
                if (j == 0) {
                    d[i][j] = d[i - 1][1] % mod
                    continue
                }
                if (j == 9) {
                    d[i][j] = d[i - 1][8] % mod
                    continue
                }
                d[i][j] = (d[i - 1][j - 1] + d[i - 1][j + 1]) % mod
            }
        }
        println(d[n].sum() % mod)
    }
}

fun main() {
    `10844`().solution()
}