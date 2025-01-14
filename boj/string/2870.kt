package boj.string

import java.math.BigInteger

class `2870` {
    private val arr = mutableListOf<BigInteger>()

    private fun String.insertNums() {
        val sb = StringBuilder()
        for (ch in this) {
            if (ch.isDigit()) {
                sb.append(ch)
                continue
            }
            if (sb.isNotEmpty()) {
                val num = sb.toString()
                arr += BigInteger(num)
                sb.clear()
            }
        }
        if (sb.isNotEmpty()) {
            val num = sb.toString()
            arr += BigInteger(num)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        repeat(n) { readLine().insertNums() }
        val sb = StringBuilder()
        arr.sort()
        arr.forEach { sb.appendLine(it) }
        print(sb)
    }
}

fun main() {
    `2870`().solution()
}