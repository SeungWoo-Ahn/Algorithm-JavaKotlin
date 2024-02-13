package boj.simulation

import java.util.StringTokenizer
import kotlin.math.abs

class `15686` {
    data class Node(val x: Int, val y: Int)

    private var chickens = arrayListOf<Node>()
    private var houses = arrayListOf<Node>()
    private var arr = intArrayOf()
    private var answer = Int.MAX_VALUE

    private fun backtracking(m: Int, depth: Int, startIdx: Int) {
        if (depth == m) {
            check()
            return
        }
        for (i in startIdx until chickens.size) {
            arr[depth] = i
            backtracking(m, depth + 1, i + 1)
        }
    }

    private fun check() {
        var total = 0
        for (house in houses) {
            var minDis = Int.MAX_VALUE
            for (i in arr.indices) {
                val chicken = chickens[arr[i]]
                val dis = abs(house.x - chicken.x) + abs(house.y - chicken.y)
                if (dis < minDis) minDis = dis
            }
            total += minDis
        }
        if (total < answer) answer = total
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        for (i in 0 until N) {
            val st = StringTokenizer(readLine())
            for (j in 0 until N) {
                st.nextToken().toInt().let {
                    if (it == 1) houses.add(Node(i, j))
                    if (it == 2) chickens.add(Node(i, j))
                }
            }
        }
        arr = IntArray(M)
        backtracking(M, 0, 0)
        println(answer)
    }
}

fun main() {
    `15686`().solution()
}