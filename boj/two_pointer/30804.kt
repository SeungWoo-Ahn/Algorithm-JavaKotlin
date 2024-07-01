package boj.two_pointer

import java.util.StringTokenizer

class `30804` {
    private var fruits = intArrayOf()
    private val cnt = IntArray(10)

    private fun twoPointer(left: Int, right: Int, count: Int, type: Int, max: Int): Int {
        if (right >= fruits.size) {
            return max
        }
        var newLeft = left
        var newCount = count
        var newType = type
        var newMax = max
        if (cnt[fruits[right]]++ == 0) newType++
        newCount++
        if (newType > 2) {
            if (--cnt[fruits[left]] == 0) newType--
            newCount--
            newLeft++
        }
        newMax = maxOf(newMax, newCount)
        return twoPointer(newLeft, right + 1, newCount, newType, newMax)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        fruits = IntArray(n) { st.nextToken().toInt() }
        val maxLen = twoPointer(0, 0, 0, 0, 0)
        print(maxLen)
    }
}

fun main() {
    `30804`().solution()
}