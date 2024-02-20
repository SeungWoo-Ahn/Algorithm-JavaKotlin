package boj.sort

import java.util.PriorityQueue
import java.util.Scanner

class `5648` {
    private fun flip(input: String): Long {
        val sb = StringBuilder()
        var findFront = false
        for (i in input.length - 1 downTo 0) {
            if (!findFront && input[i] == '0') continue
            if (!findFront) findFront = true
            sb.append(input[i])
        }
        return sb.toString().toLong()
    }

    fun solution() {
        val sc = Scanner(System.`in`)
        val n = sc.nextInt()
        val pq = PriorityQueue<Long>()
        repeat(n) { pq.add(flip(sc.next())) }
        val sb = StringBuilder()
        while (pq.isNotEmpty()) { sb.append("${pq.poll()}\n") }
        println(sb)
    }
}

fun main() {
    `5648`().solution()
}