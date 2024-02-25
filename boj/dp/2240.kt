package boj.dp

import kotlin.math.max

class `2240` {
    private val trees = IntArray(1002)
    private val d = Array(1002) { Array(32) { IntArray(3) } }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (t, w) = readLine().split(" ").map { it.toInt() }
        for (i in 1 .. t) trees[i] = readLine().toInt()
        for (i in 1 .. t) {
            d[i][0][1] = d[i - 1][0][1] + if (trees[i] == 1) 1 else 0
            for (j in 1 .. w) {
                if (j > i) break
                if (trees[i] == 1) {
                    d[i][j][1] = max(d[i - 1][j - 1][2], d[i - 1][j][1]) + 1
                    d[i][j][2] = max(d[i - 1][j - 1][1], d[i - 1][j][2])
                } else {
                    d[i][j][1] = max(d[i - 1][j - 1][2], d[i - 1][j][1])
                    d[i][j][2] = max(d[i - 1][j - 1][1], d[i - 1][j][2]) + 1
                }
            }
        }
        var answer = 0
        for (i in 0 .. w) {
            answer = max(answer, max(d[t][i][1], d[t][i][2]))
        }
        println(answer)
    }
}

fun main() {
    `2240`().solution()
}