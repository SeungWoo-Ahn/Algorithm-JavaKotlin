package boj.math

class `4153` {
    private fun check(lengths: IntArray): String {
        val (w, h, l) = lengths
        return if (w * w + h * h == l * l) "right"
        else "wrong"
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        while (true) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            if (a == 0 && b == 0 && c == 0) break
            val lengths = intArrayOf(a, b, c)
            lengths.sort()
            sb.appendLine(check(lengths))
        }
        print(sb)
    }
}

fun main() {
    `4153`().solution()
}