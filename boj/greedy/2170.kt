package boj.greedy

import java.util.PriorityQueue
import java.util.Stack

class `2170` {
    data class Line(val x: Int, val y: Int): Comparable<Line> {
        override fun compareTo(other: Line): Int {
            return if (x != other.x) x - other.x
            else y - other.y
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val pq = PriorityQueue<Line>()
        repeat(n) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            pq.add(Line(x, y))
        }
        val stack = Stack<Line>()
        stack.add(pq.poll())
        while (pq.isNotEmpty()) {
            val cur = stack.pop()
            val next = pq.poll()
            if (cur.y in next.x .. next.y) {
                stack.add(Line(cur.x, next.y))
            } else if (cur.x <= next.x && cur.y >= next.y) {
                stack.add(cur)
            } else {
                stack.add(cur)
                stack.add(next)
            }
        }
        var answer = 0
        while (stack.isNotEmpty()) {
            val cur = stack.pop()
            answer += cur.y - cur.x
        }
        println(answer)
    }
}

fun main() {
    `2170`().solution()
}