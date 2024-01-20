package boj.graph

import java.util.LinkedList
import java.util.Queue

class `1697` {

    private fun movePos(cur: Int): IntArray {
        val arr = IntArray(3)
        arr[0] = cur - 1
        arr[1] = cur + 1
        arr[2] = cur * 2
        return arr
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map { it.toInt() }
        val map = IntArray(100_001)
        val visited = BooleanArray(100_001)
        val q: Queue<Int> = LinkedList<Int>().apply {
            offer(N)
            visited[N] = true
        }

        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (cur == K) {
                println(map[cur])
                return@with
            }
            val nextPos = movePos(cur)
            for (i in nextPos.indices) {
                val nP = nextPos[i]
                if (nP !in 0 .. 100_000 || visited[nP]) continue
                q.offer(nP)
                map[nP] = map[cur] + 1
                visited[nP] = true
            }
        }
    }
}

fun main() {
    `1697`().solution()
}