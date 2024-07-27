package boj.string

import java.util.PriorityQueue

class `31925` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val pq = PriorityQueue<Pair<Int, String>>(compareBy { it.first })
        for (i in 0 until n) {
            val (name, enrolled, awarded, topRank, apcRank) = readLine().split(" ")
            if (enrolled == "hewhak") continue
            if (awarded == "winner") continue
            if (topRank.toInt() != -1 && topRank.toInt() <= 3) continue
            pq += apcRank.toInt() to name
        }
        val contenders = mutableListOf<String>()
        val m = minOf(10, pq.size)
        repeat(m) {
            contenders += pq.poll().second
        }
        contenders.sort()
        val sb = StringBuilder()
        sb.appendLine(m)
        contenders.forEach { sb.appendLine(it) }
        print(sb)
    }
}

fun main() {
    `31925`().solution()
}