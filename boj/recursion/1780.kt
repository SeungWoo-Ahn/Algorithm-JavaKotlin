package boj.recursion

import java.util.StringTokenizer

class `1780` {

    private var paper: Array<IntArray> = arrayOf()
    private val answers = IntArray(3)

    private fun recursion(x: Int, y: Int, n: Int) {
        if (check(x, y, n)) {
            answers[paper[x][y] + 1]++
            return
        }
        val s = n / 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
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
        println("${answers[0]}\n${answers[1]}\n${answers[2]}")
    }
}

fun main() {
    `1780`().solution()
}