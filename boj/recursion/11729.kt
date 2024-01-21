package boj.recursion

import java.lang.StringBuilder
import kotlin.math.pow

class `11729` {
    private val sb = StringBuilder()

    private fun recursion(a: Int, b: Int, n: Int) {
        if (n == 1) {
            sb.append("$a $b\n")
            return
        }
        recursion(a, 6 - a - b, n - 1)
        sb.append("$a $b\n")
        recursion(6 - a - b, b, n -1)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        sb.append("${(2.0.pow(n) - 1).toInt()}\n")
        recursion(1, 3, n)
        println(sb)
    }
}

fun main() {
    `11729`().solution()
}