package boj.stack

import java.util.Stack

class `2504` {
    private val stack = Stack<Char>()

    private fun calc(text: String): Int {
        var result = 0
        var intermediateResult = 1
        for (i in text.indices) {
            when (val char = text[i]) {
                '(' -> {
                    intermediateResult *= 2
                    stack.push(char)
                }
                '[' -> {
                    intermediateResult *= 3
                    stack.push(char)
                }
                ')' -> {
                    if (!isCorrect(char)) return 0
                    if (text[i - 1] == oppositeChar(char))
                        result += intermediateResult
                    stack.pop()
                    intermediateResult /= 2
                }
                ']' -> {
                    if (!isCorrect(char)) return 0
                    if (text[i - 1] == oppositeChar(char))
                        result += intermediateResult
                    stack.pop()
                    intermediateResult /= 3
                }
            }
        }
        return if (stack.isEmpty()) result
        else 0
    }

    private fun oppositeChar(char: Char): Char {
        return when (char) {
            ')' -> '('
            ']' -> '['
            else -> throw IllegalArgumentException()
        }
    }

    private fun isCorrect(char: Char): Boolean {
        return stack.isNotEmpty() && stack.peek() == oppositeChar(char)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val text = readLine()
        print(calc(text))
    }
}

fun main() {
    `2504`().solution()
}