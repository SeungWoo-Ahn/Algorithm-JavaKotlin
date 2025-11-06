package boj.dp

import java.util.StringTokenizer

class `25515` {
    private lateinit var childNodes: Array<MutableList<Int>>
    private lateinit var cost: IntArray

    private fun dfs(cur: Int): Long {
        var sum = cost[cur].toLong()
        for (child in childNodes[cur]) {
            val childCost = dfs(child)
            if (childCost > 0) {
                sum += childCost
            }
        }
        return sum
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        childNodes = Array(n) { mutableListOf() }
        repeat(n - 1) {
            val (p, c) = readLine().split(" ").map(String::toInt)
            childNodes[p] += c
        }
        val st = StringTokenizer(readLine())
        cost = IntArray(n) { st.nextToken().toInt() }
        print(dfs(0))
    }
}

fun main() {
    `25515`().solution()
}