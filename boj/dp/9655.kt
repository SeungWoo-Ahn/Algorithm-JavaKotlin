package boj.dp

class `9655` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n % 2 == 0) println("CY")
        else println("SK")
    }
}

fun main() {
    `9655`().solution()
}