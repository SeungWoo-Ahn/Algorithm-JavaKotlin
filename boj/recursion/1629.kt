package boj.recursion

class `1629` {

    private fun recursion(a: Long, b: Long, c: Long): Long {
        if (b == 1L) return a % c
        var value = recursion(a, b / 2, c)
        value = value * value % c
        if (b % 2L == 0L) return value
        return value * a % c
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (a, b, c) = readLine().split(" ").map { it.toLong() }
        println(recursion(a, b, c))
    }
}

fun main() {
    `1629`().solution()
}