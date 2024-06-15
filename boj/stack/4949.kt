package boj.stack

import java.util.Stack

class `4949` {
    private val stack = Stack<Char>()

    private fun check(text: String): String {
        stack.clear()
        for (char in text) {
            when (char) {
                '(', '[' -> stack.push(char)
                ')' -> {
                    if (stack.isNotEmpty() && stack.peek() == '(') stack.pop()
                    else return "no"
                }
                ']' -> {
                    if (stack.isNotEmpty() && stack.peek() == '[') stack.pop()
                    else return "no"
                }
            }
        }
        return if (stack.isEmpty()) "yes"
        else "no"
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        while (true) {
            val text = readLine()
            if (text == ".") break
            sb.appendLine(check(text))
        }
        print(sb)
    }
}

fun main() {
    `4949`().solution()
}