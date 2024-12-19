package boj.dp

import java.util.StringTokenizer

class `11066` {
    private var costs = intArrayOf()
    private var prefixSum = intArrayOf()
    private var cache: Array<IntArray> = arrayOf()

    private fun dp(st: Int, en: Int): Int {
        if (st == en) {
            return costs[st]
        }
        if (cache[st][en] != -1) {
            return cache[st][en]
        }
        var min = INF
        for (i in st until en) {
            min = minOf(min, dp(st, i) + dp(i + 1, en))
        }
        val sum = prefixSum[en + 1] - prefixSum[st]
        cache[st][en] = min + sum
        return cache[st][en]
    }

    private fun solve(k: Int): Int {
        var min = INF
        for (i in 0 until k - 1) {
            min = minOf(min, dp(0, i) + dp(i + 1, k - 1))
        }
        return min
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        var st: StringTokenizer
        repeat(t) {
            val k = readLine().toInt()
            st = StringTokenizer(readLine())
            costs = IntArray(k) { st.nextToken().toInt() }
            prefixSum = IntArray(k + 1)
            cache = Array(k) { IntArray(k) { -1 } }
            for (i in 1..k) {
                prefixSum[i] = prefixSum[i - 1] + costs[i - 1]
            }
            val result = solve(k)
            sb.appendLine(result)
        }
        print(sb)
    }

    companion object {
        private const val INF = Int.MAX_VALUE
    }
}

fun main() {
    `11066`().solution()
}