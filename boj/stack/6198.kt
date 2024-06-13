package boj.stack

import java.util.Stack

class `6198` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val stack = Stack<Int>()
        var answer = 0L
        repeat(n) {
            val height = readLine().toInt()
            while (stack.isNotEmpty() && stack.peek() <= height)
                stack.pop()
            answer += stack.size
            stack.push(height)
        }
        print(answer)
    }
}

fun main() {
    `6198`().solution()
}