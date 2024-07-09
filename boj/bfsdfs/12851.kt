package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `12851` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        if (k <= n) {
            print("${n - k}\n1")
            return
        }
        val q = LinkedList<Int>() as Queue<Int>
        val times = IntArray(MAX_TIME)
        val commands = listOf(MOVE_BACK, BLINK, MOVE_FORWARD)
        var minTime = MAX_TIME
        var cnt = 0
        q += n
        times[n] = 1
        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (minTime < times[cur]) break
            for (c in commands) {
                val nxt = when (c) {
                    MOVE_BACK -> cur - 1
                    BLINK -> cur * 2
                    MOVE_FORWARD -> cur + 1
                    else -> throw IllegalArgumentException()
                }
                if (nxt !in times.indices) continue
                if (nxt == k) {
                    minTime = times[cur]
                    cnt++
                }
                if (times[nxt] == 0 || times[nxt] == times[cur] + 1) {
                    q += nxt
                    times[nxt] = times[cur] + 1
                }
            }
        }
        print("$minTime\n$cnt")
    }

    companion object {
        private const val MOVE_BACK = 0
        private const val MOVE_FORWARD = 1
        private const val BLINK = 2
        private const val MAX_TIME = 100_001
    }
}

fun main() {
    `12851`().solution()
}