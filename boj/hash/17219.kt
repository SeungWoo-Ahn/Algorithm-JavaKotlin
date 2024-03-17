package boj.hash

import java.util.HashMap

class `17219` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val map = HashMap<String, String>()
        repeat(n) {
            val (url, password) = readLine().split(" ")
            map[url] = password
        }
        val sb = StringBuilder()
        repeat(m) {
            sb.append("${map[readLine()]}\n")
        }
        println(sb)
    }
}

fun main() {
    `17219`().solution()
}