package boj.dp

import java.util.StringTokenizer

class `24232` {
    private data class Edge(
        val idx: Int,
        val to: Int,
        val flipped: Boolean,
    )

    private lateinit var edges: Array<MutableList<Edge>>
    private lateinit var flip: BooleanArray
    private lateinit var result: BooleanArray
    private var minFlipCnt = Int.MAX_VALUE

    private fun input() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        edges = Array(n + 1) { mutableListOf() }
        flip = BooleanArray(n - 1)
        var st: StringTokenizer
        repeat(n - 1) { idx ->
            st = StringTokenizer(readLine())
            val u = st.nextToken().toInt()
            val v = st.nextToken().toInt()
            edges[u] += Edge(idx, v, false)
            edges[v] += Edge(idx, u, true)
        }
    }

    private fun getFlipCnt(node: Int, prev: Int): Int {
        var sum = 0
        for ((idx, to, flipped) in edges[node]) {
            if (to == prev) continue
            flip[idx] = flipped
            sum += getFlipCnt(to, node) + if (flipped) 1 else 0
        }
        return sum
    }

    private fun dfs(node: Int, prev: Int, flipCnt: Int) {
        if (flipCnt < minFlipCnt) {
            minFlipCnt = flipCnt
            result = flip.copyOf()
        }
        for((idx, to, flipped) in edges[node]) {
            if (to == prev) continue
            if (flipped) {
                flip[idx] = false
                dfs(to, node, flipCnt - 1)
                flip[idx] = true
            } else {
                flip[idx] = true
                dfs(to, node, flipCnt + 1)
                flip[idx] = false
            }
        }
    }

    fun solution() {
        input()
        val flipCnt = getFlipCnt(1, 0)
        dfs(1, 0, flipCnt)
        val sb = StringBuilder()
        result.forEach { if (it) sb.append('1') else sb.append('0') }
        print(sb)
    }
}

fun main() {
    `24232`().solution()
}