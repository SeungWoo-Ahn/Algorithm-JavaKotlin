package boj.hash

import java.util.PriorityQueue

class `16165` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val teamMap = HashMap<String, PriorityQueue<String>>()
        val nameMap = HashMap<String, String>()
        repeat(n) {
            val team = readLine()
            val count = readLine().toInt()
            repeat(count) {
                val name = readLine()
                if (teamMap.containsKey(team)) {
                    teamMap[team]?.add(name)
                } else {
                    teamMap[team] = PriorityQueue<String>().apply {
                        add(name)
                    }
                }
                nameMap[name] = team
            }
        }
        val sb = StringBuilder()
        repeat(m) {
            val quiz = readLine()
            val sort = readLine().toInt()
            if (sort == 0) {
                val teamQ = teamMap[quiz]!!
                val teamSb = StringBuilder()
                while (teamQ.isNotEmpty()) {
                    teamSb.append("${teamQ.poll()}\n")
                }
                sb.append(teamSb)
            } else {
                sb.append("${nameMap[quiz]}\n")
            }
        }
        println(sb)
    }
}

fun main() {
    `16165`().solution()
}