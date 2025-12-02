package boj.dp

import java.util.StringTokenizer

class `27501` {
    private enum class Color {
        R, G, B;

        val idx: Int get() = Color.entries.indexOf(this)
    }

    private lateinit var adj: Array<MutableList<Int>>
    private lateinit var beauty: Array<IntArray>
    private lateinit var dp: Array<IntArray>
    private lateinit var visited: BooleanArray

    private fun dfs(parentNode: Int, parentColor: Color): Int {
        if (dp[parentNode][parentColor.idx] != EMPTY) {
            return dp[parentNode][parentColor.idx]
        }
        var sum = 0
        for (nxtNode in adj[parentNode]) {
            if (visited[nxtNode]) continue
            visited[nxtNode] = true
            var max = 0
            for (color in Color.entries) {
                if (color == parentColor) continue
                max = maxOf(max, dfs(nxtNode, color))
            }
            sum += max
            visited[nxtNode] = false
        }
        return (sum + beauty[parentNode][parentColor.idx]).also {
            dp[parentNode][parentColor.idx] = it
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        adj = Array(n + 1) { mutableListOf() }
        repeat(n - 1) {
            st = StringTokenizer(readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            adj[a] += b
            adj[b] += a
        }
        beauty = Array(n + 1) { IntArray(Color.entries.size) }
        repeat(n) { i ->
            st = StringTokenizer(readLine())
            val r = st.nextToken().toInt()
            val g = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            beauty[i + 1][Color.R.idx] = r
            beauty[i + 1][Color.G.idx] = g
            beauty[i + 1][Color.B.idx] = b
        }
        dp = Array(n + 1) { IntArray(Color.entries.size) }
        visited = BooleanArray(n + 1)
        adj[0] += 1
        var maxBeauty = 0
        for (color in Color.entries) {
            maxBeauty = maxOf(maxBeauty, dfs(0, color))
        }
        val dq = ArrayDeque<Pair<Int, Color>>()
        for (color in Color.entries) {
            if (dp[0][color.idx] == maxBeauty) {
                dq += 0 to color
                break
            }
        }
        val selected = Array(n + 1) { Color.R }
        while (dq.isNotEmpty()) {
            val (parentNode, parentColor) = dq.removeLast()
            selected[parentNode] = parentColor
            for (nxtNode in adj[parentNode]) {
                if (visited[nxtNode]) continue
                visited[nxtNode] = true
                var (max, maxColor) = 0 to Color.R
                for (color in Color.entries) {
                    if (color == parentColor) continue
                    if (dp[nxtNode][color.idx] > max) {
                        max = dp[nxtNode][color.idx]
                        maxColor = color
                    }
                }
                dq += nxtNode to maxColor
            }
        }
        val sb = StringBuilder()
        sb.appendLine(maxBeauty)
        (1..n).forEach {
            sb.append(selected[it])
        }
        print(sb)
    }

    companion object {
        private const val EMPTY = 0
    }
}

fun main() {
    `27501`().solution()
}