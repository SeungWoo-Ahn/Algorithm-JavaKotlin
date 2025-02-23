package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `17071` {
    private sealed interface MoveStrategy {
        fun nextPos(curPos: Int): Int

        data object Jump : MoveStrategy {
            override fun nextPos(curPos: Int): Int {
                return curPos shl 1
            }
        }

        data object Forward : MoveStrategy {
            override fun nextPos(curPos: Int): Int {
                return curPos + 1
            }
        }

        data object Backward : MoveStrategy {
            override fun nextPos(curPos: Int): Int {
                return curPos - 1
            }
        }
    }

    private fun bfs(n: Int, k: Int): Int {
        val q = LinkedList<Int>() as Queue<Int>
        val visited = Array(500_001) { BooleanArray(2) }
        val moveStrategies = listOf(MoveStrategy.Jump, MoveStrategy.Forward, MoveStrategy.Backward)
        var curK = k
        var time = 0
        q += n
        visited[n][0] = true
        while (q.isNotEmpty()) {
            val nxtIdx = (time + 1) % 2
            repeat(q.size) {
                val curN = q.poll()
                if (curN == curK) {
                    return time
                }
                for (moveStrategy in moveStrategies) {
                    val nextN = moveStrategy.nextPos(curN)
                    if (outOfBoundary(nextN) || visited[nextN][nxtIdx]) continue
                    q += nextN
                    visited[nextN][nxtIdx] = true
                }
            }
            time++
            curK += time
            if (outOfBoundary(curK)) break
            if (visited[curK][nxtIdx]) return time
        }
        return -1
    }

    private fun outOfBoundary(x: Int): Boolean {
        return x !in 0..500_000
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val result = bfs(n, k)
        print(result)
    }
}

fun main() {
    `17071`().solution()
}