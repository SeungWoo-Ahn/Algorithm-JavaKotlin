package boj.tree

class `22856` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val parent = IntArray(n + 1)
        val lc = IntArray(n + 1)
        val rc = IntArray(n + 1)
        val visited = BooleanArray(n + 1)
        parent[ROOT] = -1
        repeat(n) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            lc[a] = b
            rc[a] = c
            if (b != LEAF) parent[b] = a
            if (c != LEAF) parent[c] = a
        }
        var endPoint = ROOT
        while (rc[endPoint] != -1)
            endPoint = rc[endPoint]
        var cur = ROOT
        var cnt = 0
        while (true) {
            visited[cur] = true
            cnt++
            cur = if (lc[cur] != LEAF && !visited[lc[cur]])
                lc[cur]
            else if (rc[cur] != LEAF && !visited[rc[cur]])
                rc[cur]
            else {
                if (cur == endPoint)
                    break
                else
                    parent[cur]
            }
        }
        println(cnt - 1)
    }

    companion object {
        private const val ROOT = 1
        private const val LEAF = -1
    }
}

fun main() {
    `22856`().solution()
}