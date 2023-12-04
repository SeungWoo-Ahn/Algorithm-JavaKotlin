package programmers.Practive.Level2

import java.util.Stack

class RotateBracket {
    fun solution(s: String): Int {
        var answer = 0
        for (i in s.indices) {
            val before = s.slice(0 until i)
            val after = s.slice(i until s.length)
            val rotatedS = after + before
            if (isCorrectBracket(rotatedS)) {
                answer++
            }
        }
        return answer
    }
    private fun isCorrectBracket(s: String): Boolean {
        val stack = Stack<Char>()
        s.toCharArray().forEach {
            if (it == '[' || it == '(' || it == '{') {
                stack.push(it)
            } else {
                when(it) {
                    ']' -> if (stack.isEmpty() || stack.peek() != '[') return false
                    ')' -> if (stack.isEmpty() || stack.peek() != '(') return false
                    '}' -> if (stack.isEmpty() || stack.peek() != '{') return false
                }
                stack.pop()
            }
        }
        return stack.isEmpty()
    }
}

fun main() {
    println(RotateBracket().solution("}]()[{"))
}