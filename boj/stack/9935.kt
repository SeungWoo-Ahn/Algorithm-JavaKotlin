package boj.stack

import java.util.Stack

class `9935` {
    private fun explode(text: String, bomb: String): String {
        val lastKey = bomb.last()
        val q = ArrayDeque<Char>()
        val stack = Stack<Char>()
        for (char in text) {
            if (char == lastKey) {
                stack.push(char)
                var idx = bomb.lastIndex
                while (idx > 0 && q.isNotEmpty() && q.last() == bomb[idx - 1]) {
                    stack.push(q.removeLast())
                    idx--
                }
                if (idx == 0) stack.clear()
                else {
                    while (stack.isNotEmpty()) {
                        q.addLast(stack.pop())
                    }
                }
            } else q.addLast(char)
        }
        return if (q.isNotEmpty()) q.joinToString("")
        else "FRULA"
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val text = readLine()
        val bomb = readLine()
        val result = explode(text, bomb)
        print(result)
    }
}

fun main() {
    `9935`().solution()
}