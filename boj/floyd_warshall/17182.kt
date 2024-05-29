package boj.floyd_warshall

import java.util.StringTokenizer

class `17182` {
    private var map: Array<IntArray> = arrayOf()
    private val planets = mutableListOf<Int>()
    private var minDistance = Int.MAX_VALUE

    private fun floyd(n: Int) {
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    map[i][j] = minOf(map[i][j], map[i][k] + map[k][j])
                }
            }
        }
    }

    private fun addPlanets(n: Int, k: Int) {
        for (i in 0 until n) {
            if (i == k) continue
            planets.add(i)
        }
    }

    private fun combination(
        n: Int,
        k: Int,
        depth: Int = 0,
        arr: IntArray = IntArray(n),
        visited: BooleanArray = BooleanArray(n)
    ) {
        if (depth == n) {
            calcDistance(k, arr)
            return
        }
        for (i in 0 until n) {
            if (visited[i]) continue
            visited[i] = true
            arr[depth] = i
            combination(n, k, depth + 1, arr, visited)
            visited[i] = false
        }
    }

    private fun calcDistance(k: Int, arr: IntArray) {
        var cur = k
        var distance = 0
        for (idx in arr) {
            distance += map[cur][planets[idx]]
            cur = planets[idx]
        }
        minDistance = minOf(minDistance, distance)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        map = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        floyd(n)
        addPlanets(n, k)
        combination(n - 1, k)
        println(minDistance)
    }
}

fun main() {
    `17182`().solution()
}