package boj.dp

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `1516` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val times = IntArray(n + 1)
        val inDegree = IntArray(n + 1)
        val dp = IntArray(n + 1)
        val adj = Array(n + 1) { mutableListOf<Int>() }
        var st: StringTokenizer
        repeat(n) {
            st = StringTokenizer(readLine())
            val num = it + 1
            val time = st.nextToken().toInt()
            times[num] = time
            while (true) {
                val parent = st.nextToken().toInt()
                if (parent == -1) break
                adj[parent] += num
                inDegree[num]++
            }
        }
        val q = LinkedList<Int>() as Queue<Int>
        for (num in 1..n) {
            if (inDegree[num] == 0) {
                dp[num] = times[num]
                q += num
            }
        }
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (child in adj[cur]) {
                dp[child] = maxOf(dp[child], dp[cur])
                if (--inDegree[child] == 0) {
                    dp[child] += times[child]
                    q += child
                }
            }
        }
        val sb = StringBuilder()
        for (num in 1..n) {
            sb.appendLine(dp[num])
        }
        print(sb)
    }
}

fun main() {
    `1516`().solution()
}