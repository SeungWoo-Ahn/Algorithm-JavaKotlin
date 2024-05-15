package boj.tree

import java.util.StringTokenizer

class `14267` {
    private var child: Array<MutableList<Int>> = arrayOf()
    private var score = intArrayOf()
    private var compliment = intArrayOf()

    private fun dfs(cur: Int, weight: Int) {
        compliment[cur] = weight
        for (nxt in child[cur])
            dfs(nxt, weight + score[nxt])
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        child = Array(n + 1) { mutableListOf() }
        score = IntArray(n + 1)
        compliment = IntArray(n + 1)
        val st = StringTokenizer(readLine())
        repeat(n) { i ->
            val parent = st.nextToken().toInt()
            if (parent != ROOT)
                child[parent].add(i + 1)
        }
        repeat(m) {
            val (i, w) = readLine().splitToInt()
            score[i] += w
        }
        for (i in child[1])
            dfs(i, score[i])
        val sb = StringBuilder()
        for (i in 1 .. n)
            sb.append("${compliment[i]} ")
        println(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val ROOT = -1
    }
}

fun main() {
    `14267`().solution()
}