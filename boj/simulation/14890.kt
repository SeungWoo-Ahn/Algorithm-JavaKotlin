package boj.simulation

import java.util.StringTokenizer
import kotlin.math.abs

class `14890` {
    private var map: Array<IntArray> = arrayOf()

    private fun addSlide(arr: IntArray, l: Int): Int {
        var idx = 0
        var cnt = 1
        while (idx < arr.size - 1) {
            val cur = arr[idx]
            val next = arr[idx + 1]
            if (abs(cur - next) > 1) return 0
            if (cur == next) {
                cnt++
                idx++
            } else if (cur < next) {
                if (cnt < l) return 0
                cnt = 1
                idx++
            } else {
                if (idx + l >= arr.size) return 0
                for (i in idx + 1 until idx + l) {
                    if (arr[i] != arr[i + 1]) return 0
                }
                idx += l
                cnt = 0
            }
        }
        return 1
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, l) = readLine().split(" ").map { it.toInt() }
        map = Array(n) {
            val st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }

        var answer = 0

        for (i in 0 until n) {
            answer += addSlide(map[i], l)
        }

        for (i in 0 until n) {
            answer += addSlide(IntArray(n) { j -> map[j][i] }, l)
        }

        println(answer)
    }
}

fun main() {
    `14890`().solution()
}