package boj.dp

class `1915` {
    private val a = Array(1001) { IntArray(1001) }
    private val d = Array(1001) { IntArray(1001) }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        for (i in 1 .. n) {
            val arr = readLine()
            for (j in 1 .. m) {
                a[i][j] = arr[j - 1].digitToInt()
            }
        }
        var max = 0
        for (i in 0 .. n) {
            for (j in 0 .. m) {
                if (a[i][j] == 1) {
                    d[i][j] = minOf(d[i][j - 1], d[i - 1][j], d[i - 1][j - 1]) + 1
                    max = maxOf(max, d[i][j])
                }
            }
        }
        println(max * max)
    }
}

fun main() {
    `1915`().solution()
}