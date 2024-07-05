package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `16953` {
    private val operations = listOf(MULTIPLY_TWO, ADD_ONE_RIGHT)
    private val set = HashSet<Int>()

    private fun bfs(a: Int, b: Int): Int {
        var bCnt = -1
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        q += a to 1
        set += a
        while (q.isNotEmpty()) {
            val (cur, cnt) = q.poll()
            if (cur == b) {
                bCnt = cnt
                break
            }
            for (o in operations) {
                val nxt = when (o) {
                    MULTIPLY_TWO -> cur * 2L
                    ADD_ONE_RIGHT -> cur * 10L + 1
                    else -> throw IllegalArgumentException()
                }
                if (nxt > b || !set.add(nxt.toInt())) continue
                q += nxt.toInt() to cnt + 1
            }
        }
        return bCnt
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        print(bfs(a, b))
    }

    companion object {
        private const val MULTIPLY_TWO = 0
        private const val ADD_ONE_RIGHT = 1
    }
}

fun main() {
    `16953`().solution()
}