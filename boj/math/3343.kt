package boj.math

class `3343` {
    private fun getMinCnt(total: Long, cnt: Long): Long {
        return if (total % cnt == 0L) total / cnt
        else total / cnt + 1
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        var (n, a, b, c, d) = readLine().split(" ").map { it.toLong() }
        if (b * c < a * d) {
            val tempCnt = a
            val tempPrice = b
            a = c
            c = tempCnt
            b = d
            d = tempPrice
        }
        var minPrice = Long.MAX_VALUE
        for (x in 0L until c) {
            val left = n - a * x
            val y = if (left < 0) 0 else getMinCnt(left, c)
            minPrice = minOf(minPrice, b * x + d * y)
            if (left < 0) {
                break
            }
        }
        print(minPrice)
    }
}

fun main() {
    `3343`().solution()
}