package boj.topological_sort

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.max

class `1005` {
    private var time = intArrayOf()
    private var dp = intArrayOf()
    private var inDegree = intArrayOf()
    private var adj: Array<MutableList<Int>> = arrayOf()

    private fun solve(n: Int, target: Int): Int {
        val q = LinkedList<Int>() as Queue<Int>
        for (i in 1 .. n) {
            if (inDegree[i] == 0) {
                if (i == target) return time[i]
                q.offer(i)
                dp[i] = time[i]
            }
        }
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (nxt in adj[cur]) {
                inDegree[nxt]--
                dp[nxt] = max(dp[nxt], dp[cur] + time[nxt])
                if (inDegree[nxt] == 0) {
                    if (nxt == target) return dp[nxt]
                    q.offer(nxt)
                }
            }
        }
        return dp[target]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        var st: StringTokenizer
        val sb = StringBuilder()
        repeat(t) {
            val (n, k) = readLine().splitToInt()
            time = IntArray(n + 1)
            dp = IntArray(n + 1)
            inDegree = IntArray(n + 1)
            adj = Array(n + 1) { mutableListOf() }
            st = StringTokenizer(readLine())
            for (i in 1 .. n) {
                time[i] = st.nextToken().toInt()
            }
            repeat(k) {
                val (x, y) = readLine().splitToInt()
                adj[x].add(y)
                inDegree[y]++
            }
            val target = readLine().toInt()
            sb.appendLine(solve(n, target))
        }
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `1005`().solution()
}