package boj.stack

import java.util.Stack
import java.util.StringTokenizer

class `2493` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val stack = Stack<Pair<Int, Int>>()
        val answer = IntArray(n)
        repeat(n) { idx ->
            val height = st.nextToken().toInt()
            while (stack.isNotEmpty()) {
                if (stack.peek().first > height) break
                stack.pop()
            }
            if (stack.isEmpty()) answer[idx] = 0
            else answer[idx] = stack.peek().second
            stack.push(height to idx + 1)
        }
        print(answer.joinToString(" "))
    }
}

fun main() {
    `2493`().solution()
}