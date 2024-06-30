package boj.math

class `18403` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val givenYears = readLine()
                .split(", ")
                .map { it.toInt() }
                .filter { it % 4 == 0 }
                .joinToString(" ")
            sb.appendLine(givenYears)
        }
        print(sb)
    }
}

fun main() {
    `18403`().solution()
}