package boj.math

class `2960` {
    private val table = BooleanArray(1001) { false }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        var cnt = 0
        for (i in 2 .. n) {
            if (table[i]) continue
            table[i] = true
            if (++cnt == k) {
                println(i)
                return
            }
            for (j in i * i .. n step i) {
                if (table[j]) continue
                table[j] = true
                if (++cnt == k) {
                    println(j)
                    return
                }
            }
        }
    }
}

fun main() {
    `2960`().solution()
}