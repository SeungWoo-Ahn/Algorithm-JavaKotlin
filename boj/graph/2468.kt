package boj.graph

class `2468` {
    private var visited = Array(0) { BooleanArray(0) { false } }
    private var map = Array(0) { IntArray(0) { 0 } }
    private val dx = listOf(1, -1, 0, 0)
    private val dy = listOf(0, 0, 1, -1)

    private fun dfs(x: Int, y: Int, t: Int, N: Int) {
        visited[x][y] = true
        for (i in dx.indices) {
            val newX = x + dx[i]
            val newY = y + dy[i]
            if (newX < 0 || newX >= N || newY < 0 || newY >= N) continue
            if (map[newX][newY] >= t && !visited[newX][newY]) {
                dfs(newX, newY, t, N)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        var max = 2
        var min = 100
        map = Array(N) { IntArray(N) { 0 } }.apply {
            for (j in 0 until N) {
                val line = readLine().split(" ").map { it.toInt() }
                for (i in 0 until N) {
                    line[i].let {
                        this[i][j] = it
                        if (it > max) max = it
                        if (it < min) min = it
                    }
                }
            }
        }
        if (max == min) {
            println(1)
            return@with
        }
        var answer = 1
        for(t in min..max) {
            var cnt = 0
            visited = Array(N) { BooleanArray(N) { false } }
            for (x in 0 until  N) {
                for (y in 0 until  N) {
                    if (map[x][y] >= t && !visited[x][y]) {
                        dfs(x, y, t, N)
                        cnt++
                    }
                }
            }
            if (cnt > answer) answer = cnt
        }
        println(answer)
    }
}

fun main() {
    `2468`().solution()
}