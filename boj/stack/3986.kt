package boj.stack

import java.util.Stack

class `3986` {
    private val stack = Stack<Char>()

    private fun isGoodWords(words: String): Boolean {
        stack.clear()
        for (word in words) {
            if (stack.isNotEmpty() && word == stack.peek()) stack.pop()
            else stack.push(word)
        }
        return stack.isEmpty()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var goodWordsCnt = 0
        repeat(n) {
            val words = readLine()
            if (isGoodWords(words))
                goodWordsCnt++
        }
        print(goodWordsCnt)
    }
}

fun main() {
    `3986`().solution()
}