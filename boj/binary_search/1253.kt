package boj.binary_search

import java.util.StringTokenizer

class `1253` {
    private lateinit var arr: IntArray

    private fun search(idx: Int): Int {
        var st = 0
        var en = arr.lastIndex
        val target = arr[idx]
        while (st < en) {
            if (st == idx) {
                st++
                continue
            }
            if (en == idx) {
                en--
                continue
            }
            val sum = arr[st] + arr[en]
            if (sum == target) {
                return 1
            } else if (sum < target) {
                st++
            } else {
                en--
            }
        }
        return 0
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        arr = IntArray(n) { st.nextToken().toInt() }.apply { sort() }
        var goodCnt = 0
        for (i in arr.indices) {
            goodCnt += search(i)
        }
        print(goodCnt)
    }
}


fun main() {
    `1253`().solution()
}