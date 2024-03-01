package boj.greedy

import java.util.LinkedList
import java.util.Queue

class `1541` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val q = LinkedList<Int>() as Queue<Int>
        var temp = 0
        val sb = StringBuilder()
        for (i in s.indices) {
            when(s[i]) {
                '+' -> {
                    temp += sb.toString().toInt()
                    sb.clear()
                }
                '-' -> {
                    temp += sb.toString().toInt()
                    sb.clear()
                    q.offer(temp)
                    temp = 0
                }
                else -> sb.append(s[i])
            }
            if (i == s.lastIndex) {
                temp += sb.toString().toInt()
                q.offer(temp)
            }
        }
        var answer = q.poll()
        while (q.isNotEmpty()) {
            answer -= q.poll()
        }
        println(answer)
    }
}

fun main() {
    `1541`().solution()
}