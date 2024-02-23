package boj.dp

class `2193` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n == 1) {
            print(1)
            return
        }
        val d = Array(n + 1) { LongArray(2) }
        d[2][0] = 1L.also { d[2][1] = 0L }
        for (i in 3 .. n) {
            d[i][0] = d[i - 1][0] + d[i - 1][1]
            d[i][1] = d[i - 1][0]
        }
        print(d[n][0] + d[n][1])
    }
}

fun main() {
    `2193`().solution()
}