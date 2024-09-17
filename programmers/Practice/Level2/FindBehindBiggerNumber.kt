package programmers.Practice.Level2

import java.util.Stack

class FindBehindBiggerNumber {
    fun solution(numbers: IntArray): IntArray {
        val result = IntArray(numbers.size)
        val stack = Stack<Int>()
        stack += numbers.last()
        for (i in numbers.lastIndex - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.peek() <= numbers[i]) {
                stack.pop()
            }
            if (stack.isNotEmpty()) {
                result[i] = stack.peek()
            }
            stack += numbers[i]
        }
        return result
    }
}

fun main() {
    val numbers = intArrayOf(9, 1, 5, 3, 6, 2)
    val answer = FindBehindBiggerNumber().solution(numbers)
    print(answer.joinToString())
}