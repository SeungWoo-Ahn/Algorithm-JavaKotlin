package boj.dp

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `20303` {
    private var candy = intArrayOf()
    private var friendGraph: Array<MutableList<Int>> = arrayOf()
    private var check = booleanArrayOf()
    private var group = mutableListOf<Pair<Int, Int>>()

    private fun bfs(st: Int) {
        val q = LinkedList<Int>() as Queue<Int>
        q += st
        check[st] = true
        var memberCnt = 0
        var candyCnt = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            memberCnt++
            candyCnt += candy[cur]
            for (friend in friendGraph[cur]) {
                if (check[friend]) continue
                check[friend] = true
                q += friend
            }
        }
        group += memberCnt to candyCnt
    }

    private fun findMaxCandy(limit: Int): Int {
        val dp = Array(group.size + 1) { IntArray(limit + 1) }
        for (i in 1..group.size) {
            val (memberCnt, candyCnt) = group[i - 1]
            for (j in 1..limit) {
                if (memberCnt > j) dp[i][j] = dp[i - 1][j]
                else dp[i][j] = maxOf(dp[i - 1][j], dp[i - 1][j - memberCnt] + candyCnt)
            }
        }
        return dp[group.size][limit]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readLine().splitToInt()
        val st = StringTokenizer(readLine())
        candy = IntArray(n) { st.nextToken().toInt() }
        friendGraph = Array(n) { mutableListOf() }
        check = BooleanArray(n)
        repeat(m) {
            val (a, b) = readLine().splitToInt(offset = 1)
            friendGraph[a] += b
            friendGraph[b] += a
        }
        for (i in 0 until n) {
            if (check[i]) continue
            bfs(i)
        }
        print(findMaxCandy(k - 1))
    }

    private fun String.splitToInt(offset: Int = 0) = split(" ").map { it.toInt() - offset }
}

fun main() {
    `20303`().solution()
}