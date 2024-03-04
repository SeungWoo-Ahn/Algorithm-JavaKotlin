package boj.math

class `1929` {
    private val state = BooleanArray(1_000_001) { true }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (m, n) = readLine().split(" ").map { it.toInt() }
        state[1] = false
        for (i in 2 .. n) {
            if (i * i > n) break
            if (!state[i]) continue
            for (j in i * i .. n step i) {
                state[j] = false
            }
        }
        val sb = StringBuilder()
        for (i in m .. n) {
            if (state[i]) sb.append("$i\n")
        }
        println(sb)
    }
}

fun main() {
    `1929`().solution()
}