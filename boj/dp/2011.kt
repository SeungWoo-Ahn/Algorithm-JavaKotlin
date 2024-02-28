package boj.dp

class `2011` {
    private val code = IntArray(5001)
    private val d = IntArray(5001)
    private val mod = 1_000_000

    fun solution() = with(System.`in`.bufferedReader()) {
        val line = readLine()
        val n = line.length
        for (i in 1 .. n) {
            code[i] = line[i - 1] - '0'
        }
        d[0] = 1
        for (i in 1 .. n) {
            if (code[i] > 0) d[i] = (d[i] + d[i - 1]) % mod
            val x = code[i - 1] * 10 + code[i]
            if (x in 10 .. 26) d[i] = (d[i] + d[i - 2]) % mod
        }
        println(d[n])
    }
}

fun main() {
    `2011`().solution()
}