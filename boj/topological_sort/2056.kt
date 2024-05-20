package boj.topological_sort

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.max

class `2056` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val time = IntArray(n + 1)
        val inDegree = IntArray(n + 1)
        val adj = Array(n + 1) { mutableListOf<Int>() }
        val dp = IntArray(n + 1)
        var st: StringTokenizer
        repeat(n) { num ->
            st = StringTokenizer(readLine())
            time[num + 1] = st.nextToken().toInt()
            val size = st.nextToken().toInt()
            repeat(size) {
                val prevNum = st.nextToken().toInt()
                inDegree[num + 1]++
                adj[prevNum].add(num + 1)
            }
        }
        val q = LinkedList<Int>() as Queue<Int>
        for (i in 1 .. n) {
            if (inDegree[i] == 0) {
                dp[i] = time[i]
                q.offer(i)
            }
        }
        var answer = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            answer = max(answer, dp[cur])
            for (nxt in adj[cur]) {
                inDegree[nxt]--
                dp[nxt] = max(dp[nxt], dp[cur] + time[nxt])
                if (inDegree[nxt] == 0)
                    q.offer(nxt)
            }
        }
        println(answer)
    }
}

fun main() {
    `2056`().solution()
}