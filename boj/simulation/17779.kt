package boj.simulation

import java.util.StringTokenizer
import kotlin.math.min

class `17779` {
    private var A: Array<IntArray> = arrayOf()
    private var sum = 0
    private var answer = Int.MAX_VALUE

    private fun separate(n: Int, x: Int, y: Int) {
        val d1Max = min(y - 1, n - x - 1)
        val d2Max = min(n - y, n - x - 1)
        for (d1 in 1 .. d1Max) {
            for (d2 in 1 .. d2Max) {
                calc(n, x, y, d1, d2)
            }
        }
    }

    private fun calc(n: Int, x: Int, y: Int, d1: Int, d2: Int) {
        val sections = IntArray(5)
        sections[0] = calcSection(1 until x + d1, 1 .. y) { r, c ->
            if (r < x) true
            else c < -r + x + y
        }
        sections[1] = calcSection(1 .. x + d2, y + 1 .. n) { r, c ->
            if (r < x) true
            else c > r - x + y
        }
        sections[2] = calcSection(x + d1 .. n, 1 until y - d1 + d2) { r, c ->
            if (r > x + d1 + d2) true
            else c < r - x + y - 2 * d1
        }
        sections[3] = calcSection(x + d2 + 1 .. n, y - d1 + d2 .. n) { r, c->
            if (r > x + d1 + d2) true
            else c > -r + x + y + 2 * d2
        }
        sections[4] = sum - sections.sum()
        val diff = sections.max() - sections.min()
        if (diff < answer) {
            answer = diff
        }
    }

    private fun calcSection(rRange: IntRange, cRange: IntRange, condition: (Int, Int) -> Boolean): Int {
        var population = 0
        for (r in rRange) {
            for (c in cRange) {
                if (!condition(r, c)) continue
                population += A[r][c]
            }
        }
        return population
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        A = Array(n + 1) { IntArray(n + 1) }
        repeat(n) { x ->
            val st = StringTokenizer(readLine())
            repeat(n) { y ->
                st.nextToken().toInt().let {
                    sum += it
                    A[x + 1][y + 1] = it
                }
            }
        }
        for (x in 1 .. n - 2) {
            for (y in 2 until  n) {
                separate(n, x, y)
            }
        }
        println(answer)
    }
}

fun main() {
    `17779`().solution()
}