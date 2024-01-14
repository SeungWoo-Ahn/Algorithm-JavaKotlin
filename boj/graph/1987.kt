package boj.graph

import java.util.StringTokenizer

class `1987` {
    private val dx = listOf(1, -1, 0, 0)
    private val dy = listOf(0, 0, 1, -1)
    private val visited = BooleanArray(26)
    private var maxDepth = 1

    fun solution() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine(), " ")
        val R = st.nextToken().toInt()
        val map = Array(R) { readLine().toCharArray().map { it - 'A' } }
        visited[map[0][0]] = true
        dfs(map, 0 , 0, 1)
        println(maxDepth)
    }

    private fun dfs(map: Array<List<Int>>, x: Int, y: Int, depth: Int) {
        if (depth > maxDepth) {
            maxDepth = depth
        }
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in map.indices || ny !in map[0].indices || visited[map[nx][ny]]) continue
            visited[map[nx][ny]] = true
            dfs(map, nx, ny, depth + 1)
            visited[map[nx][ny]] = false
        }
    }
}

fun main() {
    `1987`().solution()
}