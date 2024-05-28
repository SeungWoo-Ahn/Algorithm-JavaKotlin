package boj.floyd_warshall

import java.util.StringTokenizer
import kotlin.math.max

class `14938` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, r) = readLine().splitToInt()
        val itemCount = IntArray(n + 1)
        val st = StringTokenizer(readLine())
        repeat(n) { i -> itemCount[i + 1] = st.nextToken().toInt() }
        val map = Array(n + 1) { x ->
            IntArray(n + 1) { y -> if (x != y) INF else 0 }
        }
        repeat(r) {
            val (a, b, l) = readLine().splitToInt()
            map[a][b] = l
            map[b][a] = l
        }
        for (k in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1 .. n) {
                    if (map[i][k] + map[k][j] < map[i][j])
                        map[i][j] = map[i][k] + map[k][j]
                }
            }
        }
        var max = 0
        for (i in 1 .. n) {
            var total = 0
            for (j in 1 .. n) {
                if (map[i][j] > m) continue
                total += itemCount[j]
            }
            max = max(max, total)
        }
        println(max)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 16
    }
}

fun main() {
    `14938`().solution()
}