package boj.stack

import java.util.Stack

class `10773` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val k = readLine().toInt()
        val stack = Stack<Int>()
        var answer = 0L
        repeat(k) {
            when (val num = readLine().toInt()) {
                0 -> {
                    answer -= stack.pop()
                }
                else -> {
                    stack.push(num)
                    answer += num
                }
            }
        }
        print(answer)
    }
}

fun main() {
    `10773`().solution()
}