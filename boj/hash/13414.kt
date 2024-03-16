package boj.hash

import java.util.HashMap
import java.util.PriorityQueue

class `13414` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (k, L) = readLine().split(" ").map { it.toInt() }
        val map = HashMap<String, Int>()
        repeat(L) { idx ->
            val id = readLine()
            map[id] = idx
        }
        val pq = PriorityQueue<Pair<String, Int>> { o1, o2 -> o1.second - o2.second }
        val iter = map.iterator()
        while (iter.hasNext()) {
            val entry = iter.next()
            pq.add(Pair(entry.key, entry.value))
        }
        val sb = StringBuilder()
        var cnt = 0
        while (cnt < k) {
            if (pq.isEmpty()) break
            sb.append("${pq.poll().first}\n")
            cnt++
        }
        println(sb)
    }
}

fun main() {
    `13414`().solution()
}