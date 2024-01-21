package boj.recursion

import java.util.StringTokenizer

class `2630` {
    private var paper: Array<IntArray> = arrayOf()
    private val answer = IntArray(2)

    private fun recursion(x: Int, y: Int, n: Int) {
        if (check(x, y, n)) {
            answer[paper[x][y]]++
            return
        }
        val s = n / 2
        for (i in 0 until 2) {
            for (j in 0 until 2) {
                recursion(x + i * s, y + j * s, s)
            }
        }
    }

    private fun check(x: Int, y: Int, n: Int): Boolean {
        for (i in x until x + n) {
            for (j in y until y + n) {
                if (paper[i][j] != paper[x][y])
                    return false
            }
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        paper = Array(n) {
            val st = StringTokenizer(readLine())
            val intArr = IntArray(n)
            repeat(n) { i ->
                intArr[i] = st.nextToken().toInt()
            }
            intArr
        }
        recursion(0, 0, n)
        println("${answer[0]}\n${answer[1]}")
    }
}

fun main() {
    `2630`().solution()
}