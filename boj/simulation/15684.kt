package boj.simulation

import java.util.StringTokenizer

class `15684` {
    private var n = 0
    private var m = 0
    private var h = 0
    private var minCnt = 0
    private var map: Array<BooleanArray> = arrayOf()

    private fun dfs(depth: Int, max: Int) {
        if (depth == max) {
            if (isCorrect()) {
                minCnt = max
            }
            return
        }

        for (j in 1 .. n) {
            var i = 1
            while (i <= h) {
                if (map[i][j] || map[i][j - 1] || map[i][j + 1]){
                    i++
                    continue
                }
                map[i][j] = true
                dfs(depth + 1, max)
                map[i][j] = false

                while (i <= h && !map[i][j - 1] && !map[i][j + 1]) i++
            }
        }
    }

    private fun isCorrect(): Boolean {
        for (j in 1 .. n) {
            var col = j
            for (i in 1 .. h) {
                if (map[i][col]) col++
                else if (map[i][col - 1]) col--
            }
            if (col != j) return false
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        h = st.nextToken().toInt()
        map = Array(h + 2) { BooleanArray(n + 2) }

        repeat(m) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            map[a][b] = true
        }

        for (i in 0 .. 3) {
            dfs(0, i)
            if (minCnt != -1) {
                println(minCnt)
                return
            }
        }
        println(-1)
    }
}

fun main() {
    `15684`().solution()
}