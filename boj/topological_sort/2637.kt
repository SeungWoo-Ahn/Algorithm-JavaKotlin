package boj.topological_sort

import java.util.HashMap
import java.util.LinkedList
import java.util.Queue

class `2637` {
    private data class Edge(val num: Int, val cnt: Int)

    private var inDegree = intArrayOf()
    private var adj: Array<MutableList<Edge>> = arrayOf()
    private val q = LinkedList<Int>() as Queue<Int>
    private val numToIdx = HashMap<Int, Int>()
    private val idxToNum = HashMap<Int, Int>()
    private var dp: Array<IntArray> = arrayOf()
    private var baseSize = 0

    private fun findBase(n: Int) {
        var seq = 0
        for (i in 1 .. n) {
            if (inDegree[i] == 0) {
                q.offer(i)
                numToIdx[i] = seq
                idxToNum[seq] = i
                seq++
            }
        }
        baseSize = q.size
    }

    private fun initDp(n: Int) {
        dp = Array(n + 1) { IntArray(baseSize) }
        repeat(baseSize) {
            val num = q.poll()
            dp[num][numToIdx[num]!!]++
            q.offer(num)
        }
    }

    private fun countEachBase() {
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for ((nxt, cnt) in  adj[cur]) {
                for (i in 0 until baseSize) {
                    dp[nxt][i] += dp[cur][i] * cnt
                }
                if (--inDegree[nxt] == 0)
                    q.offer(nxt)
            }
        }
    }

    private fun printResult(n: Int) {
        val sb = StringBuilder()
        for (i in 0 until baseSize) {
            sb.appendLine("${idxToNum[i]} ${dp[n][i]}")
        }
        println(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        inDegree = IntArray(n + 1)
        adj = Array(n + 1) { mutableListOf() }
        repeat(m) {
            val (x, y, k) = readLine().split(" ").map { it.toInt() }
            inDegree[x]++
            adj[y].add(Edge(x, k))
        }
        findBase(n)
        initDp(n)
        countEachBase()
        printResult(n)
    }
}

fun main() {
    `2637`().solution()
}