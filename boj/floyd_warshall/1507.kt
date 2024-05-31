package boj.floyd_warshall

import java.util.StringTokenizer

class `1507` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        val map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        val unit = Array(n) { BooleanArray(n) }
        for (i in map.indices) {
            for (j in i + 1 until n) {
                var isUnit = true
                for (k in map.indices) {
                    if (k == i || k == j) continue
                    val viaKLength = map[i][k] + map[k][j]
                    if (viaKLength < map[i][j]) {
                        println(-1)
                        return
                    }
                    if (viaKLength == map[i][j]) isUnit = false
                }
                if (isUnit) {
                    unit[i][j] = true
                    unit[j][i] = true
                }
            }
        }
        var roadLength = 0
        for (i in map.indices) {
            for (j in i + 1 until n) {
                if (unit[i][j]) roadLength += map[i][j]
            }
        }
        println(roadLength)
    }
}

fun main() {
    `1507`().solution()
}