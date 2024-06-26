package boj.hash

import java.util.PriorityQueue

class `1764` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val set = HashSet<String>()
        repeat(n) { set.add(readLine()) }
        val pq = PriorityQueue<String>()
        repeat(m) {
            val name = readLine()
            if (!set.add(name)) pq.add(name)
        }
        val sb = StringBuilder().apply { appendLine(pq.size) }
        while (pq.isNotEmpty()) { sb.appendLine(pq.poll()) }
        print(sb)
    }
}

fun main() {
    `1764`().solution()
}