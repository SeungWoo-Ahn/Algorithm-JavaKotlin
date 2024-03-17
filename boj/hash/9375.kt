package boj.hash

import java.util.HashMap

class `9375` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val n = readLine().toInt()
            val map = HashMap<String, Int>()
            repeat(n) {
                val (name, sort) = readLine().split(" ")
                map[sort] = map.getOrDefault(sort, 0) + 1
            }
            var answer = 1
            map.values.forEach {
                answer *= it + 1
            }
            sb.append("${answer - 1}\n")
        }
        println(sb)
    }
}

fun main() {
    `9375`().solution()
}