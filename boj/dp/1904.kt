package boj.dp

class `1904` {
    private val d = IntArray(1_000_001)
    private val mod = 15746

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        d[1] = 1
        d[2] = 2
        for (i in 3 .. n) {
            d[i] = (d[i - 1] + d[i - 2]) % mod
        }
        println(d[n])
    }
}

fun main() {
    `1904`().solution()
}