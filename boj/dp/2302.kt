package boj.dp

import java.util.LinkedList
import java.util.Queue

class `2302` {
    private val vips = BooleanArray(42)
    private val seats = IntArray(42)
    private val d = IntArray(42)

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        repeat(m) { vips[readLine().toInt()] = true }
        var maxCnt = 0
        val q: Queue<Int> = LinkedList()
        for (i in 1 .. n) {
            if (vips[i]) continue
            seats[i] = seats[i - 1] + 1
            if (vips[i + 1] || i == n) q.offer(seats[i])
            if (seats[i] > maxCnt) maxCnt = seats[i]
        }
        d[1] = 1
        d[2] = 2
        for (i in 3 .. maxCnt) {
            d[i] = d[i - 1] + d[i - 2]
        }
        var answer = 1
        while (q.isNotEmpty()){
            answer *= d[q.poll()]
        }
        println(answer)
    }
}

fun main() {
    `2302`().solution()
}