package boj.floyd_warshall

import java.util.StringTokenizer
import kotlin.math.min

class `21940` {
    private var map: Array<IntArray> = arrayOf()
    private var friends = mutableListOf<Int>()
    private var distance = mutableListOf<Pair<Int, Int>>()

    private fun floyd(n: Int) {
        for (k in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1 .. n) {
                    map[i][j] = min(map[i][j], map[i][k] + map[k][j])
                }
            }
        }
    }

    private fun checkDistance(n: Int) {
        for (city in 1 .. n) {
            var max = 0
            friends.forEach { friend ->
                max = maxOf(max, map[friend][city] + map[city][friend])
            }
            distance += city to max
        }
    }

    private fun printResult() {
        distance.sortBy { it.second }
        val sb = StringBuilder()
        val min = distance[0].second
        for ((city, dis) in distance) {
            if (dis > min) break
            sb.append("$city ")
        }
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().splitToInt()
        map = Array(n + 1) { x ->
            IntArray(n + 1) { y -> if (x != y) INF else 0 }
        }
        repeat(m) {
            val (a, b, t) = readLine().splitToInt()
            map[a][b] = min(map[a][b], t)
        }
        val k = readLine().toInt()
        val st = StringTokenizer(readLine())
        repeat(k) { friends += st.nextToken().toInt() }
        floyd(n)
        checkDistance(n)
        printResult()
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }

    companion object {
        private const val INF = 987_987_987
    }
}

fun main() {
    `21940`().solution()
}