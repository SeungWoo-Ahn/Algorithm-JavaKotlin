package boj.minimum_spanning_tree

class `9372` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            repeat(m) { readLine() }
            sb.appendLine(n - 1)
        }
        println(sb)
    }
}

fun main() {
    `9372`().solution()
}