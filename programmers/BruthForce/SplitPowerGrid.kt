package programmers.BruthForce

import kotlin.math.absoluteValue

class SplitPowerGrid {
    private var answer = 100

    private fun towerCount(v1: Int, v2: Int, tree: Array<ArrayList<Int>>, visited: BooleanArray): Int {
        var count = 1
        val queue = ArrayDeque<Int>()
        visited[v1] = true
        queue.add(v1)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            tree[current].forEach { next ->
                if (!visited[next] && next != v2) {
                    count++
                    visited[next] = true
                    queue.add(next)
                }
            }
        }
        return count
    }

    fun solution(n: Int, wires: Array<IntArray>): Int {
        val tree = Array(n + 1) { arrayListOf<Int>() }
        wires.forEach { wire ->
            tree[wire[0]].add(wire[1])
            tree[wire[1]].add(wire[0])
        }
        wires.forEach { wire ->
            val network1 = towerCount(wire[0], wire[1], tree, BooleanArray(n + 1))
            val network2 = towerCount(wire[1], wire[0], tree, BooleanArray(n + 1))
            answer = answer.coerceAtMost((network1 - network2).absoluteValue)
        }
        return answer
    }
}

fun main() {
    val n = 9
    val wires = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
        intArrayOf(4, 6),
        intArrayOf(4, 7),
        intArrayOf(7, 8),
        intArrayOf(7, 9)
    )
    println(SplitPowerGrid().solution(n, wires))
}