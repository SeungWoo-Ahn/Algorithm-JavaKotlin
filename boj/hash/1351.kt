package boj.hash

import java.util.HashMap

class `1351` {
    private var n = 0L
    private var p = 0
    private var q = 0
    private val map = HashMap<Long, Long>()

    private fun solve(x: Long): Long {
        if (x == 0L) return 1
        if (map.containsKey(x)) return map[x]!!
        map[x] = solve(x / p) + solve(x / q)
        return map[x]!!
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val line = readLine().split(" ")
        n = line[0].toLong()
        p = line[1].toInt()
        q = line[2].toInt()
        println(solve(n))
    }
}

fun main() {
    `1351`().solution()
}