package boj.dp

class `1010` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val minN = minOf(n, m - n)
            var result = 1L
            for (i in m downTo m - minN + 1) {
                result *= i
            }
            for (i in minN downTo 2) {
                result /= i
            }
            sb.appendLine(result)
        }
        print(sb)
    }
}

fun main() {
    `1010`().solution()
}