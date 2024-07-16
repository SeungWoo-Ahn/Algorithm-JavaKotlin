package boj.dp

import java.util.Stack

class `9252` {
    private var dp: Array<IntArray> = arrayOf()
    private val stack = Stack<Char>()

    private fun fillDp(a: String, b: String) {
        val aRange = a.length + 1
        val bRange = b.length + 1
        dp = Array(aRange) { IntArray(bRange) }
        for (i in 1 until aRange) {
            for (j in 1 until bRange) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
    }

    private fun backtracking(x: Int, y: Int, a: String) {
        if (dp[x][y] == 0) return
        if (dp[x][y] == dp[x - 1][y]) {
            backtracking(x - 1, y, a)
        } else if (dp[x][y] == dp[x][y - 1]) {
            backtracking(x, y - 1, a)
        } else {
            stack.push(a[x - 1])
            backtracking(x - 1, y - 1, a)
        }
    }

    private fun printResult(a: String, b: String) {
        val sb = StringBuilder()
        sb.appendLine(dp[a.length][b.length])
        while (stack.isNotEmpty()) {
            sb.append(stack.pop())
        }
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val a = readLine()
        val b = readLine()
        fillDp(a, b)
        backtracking(a.length, b.length, a)
        printResult(a, b)
    }
}

fun main() {
    `9252`().solution()
}