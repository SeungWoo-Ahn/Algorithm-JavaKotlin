package programmers.Practice.Level3

import java.util.LinkedList
import java.util.Queue
import kotlin.math.abs

class MakeAllZeros {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var level = intArrayOf()
    private var child = intArrayOf()

    private fun makeAdj(n: Int, edges: Array<IntArray>) {
        adj = Array(n) { mutableListOf() }
        for ((u, v) in edges) {
            adj[u] += v
            adj[v] += u
        }
    }

    private fun dfs(cur: Int) {
        for (nxt in adj[cur]) {
            if (level[nxt] == 0) {
                level[nxt] = level[cur] + 1
                child[cur]++
                dfs(nxt)
            }
        }
    }

    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        val n = a.size
        makeAdj(n, edges)
        level = IntArray(n)
        child = IntArray(n)
        level[ROOT] = 1
        dfs(ROOT)
        val q = LinkedList<Int>() as Queue<Int>
        val weight = LongArray(n) { a[it].toLong() }
        var result = 0L
        for (num in 0 until n) {
            if (child[num] == 0) {
                q += num
            }
        }
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (nxt in adj[cur]) {
                if (level[cur] < level[nxt]) continue
                result += abs(weight[cur])
                weight[nxt] += weight[cur]
                weight[cur] = 0
                if (--child[nxt] == 0) {
                    q += nxt
                }
            }
        }
        return if (weight[ROOT] == 0L) result
        else -1
    }

    companion object {
        private const val ROOT = 0
    }
}

fun main() {
    val a = intArrayOf(-5, 0, 2, 1, 2)
    val edges = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(3, 4),
        intArrayOf(2, 3),
        intArrayOf(0, 3)
    )
    val answer = MakeAllZeros().solution(a, edges)
    print(answer)
}