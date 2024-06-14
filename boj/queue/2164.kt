package boj.queue

import java.util.LinkedList
import java.util.Queue

class `2164` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val q = LinkedList<Int>() as Queue<Int>
        (1 .. n).forEach(q::add)
        while (q.size > 1) {
            q.poll()
            q.offer(q.poll())
        }
        print(q.peek())
    }
}

fun main() {
    `2164`().solution()
}