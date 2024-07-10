package boj.dp

import java.util.StringTokenizer

class `11054` {
    private var arr = intArrayOf()
    private var leftDp = intArrayOf()
    private var rightDp = intArrayOf()

    private fun fillLeftDp(n: Int) {
        leftDp = IntArray(n) { 1 }
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (arr[i] > arr[j] && leftDp[i] < leftDp[j] + 1) {
                    leftDp[i] = leftDp[j] + 1
                }
            }
        }
    }

    private fun fillRightDp(n: Int) {
        rightDp = IntArray(n) { 1 }
        for (i in n - 1 downTo 0) {
            for (j in n - 1 downTo i + 1) {
                if (arr[i] > arr[j] && rightDp[i] < rightDp[j] + 1) {
                    rightDp[i] = rightDp[j] + 1
                }
            }
        }
    }

    private fun findMax(n: Int): Int {
        var max = 0
        for (i in 0 until n) {
            max = maxOf(max, leftDp[i] + rightDp[i])
        }
        return max - 1
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        arr = IntArray(n) { st.nextToken().toInt() }
        fillLeftDp(n)
        fillRightDp(n)
        print(findMax(n))
    }
}

fun main() {
    `11054`().solution()
}