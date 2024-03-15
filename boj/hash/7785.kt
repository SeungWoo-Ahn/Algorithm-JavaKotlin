package boj.hash

import java.util.Collections
import java.util.PriorityQueue

class `7785` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val set = HashSet<String>()
        repeat(n) {
            val (name, o) = readLine().split(" ")
            if (o == "enter") {
                set.add(name)
            } else {
                set.remove(name)
            }
        }
        val pq = PriorityQueue<String>(Collections.reverseOrder())
        set.forEach { pq.add(it) }
        val sb = StringBuilder()
        while (pq.isNotEmpty()) { sb.append("${pq.poll()}\n") }
        println(sb)
    }
}

fun main() {
    `7785`().solution()
}