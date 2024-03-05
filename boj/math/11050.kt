package boj.math

class `11050` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        var answer = 1
        for (i in 1 .. n) answer *= i
        for (i in 1 .. k) answer /= i
        for (i in 1 .. n - k) answer /= i
        println(answer)
    }
}

fun main() {
    `11050`().solution()
}