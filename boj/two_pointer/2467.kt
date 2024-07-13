package boj.two_pointer

import java.util.StringTokenizer

class `2467` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        if (arr.first() > 0) {
            print("${arr[0]} ${arr[1]}")
            return
        }
        if (arr.last() < 0) {
            print("${arr[n - 2]} ${arr[n - 1]}")
            return
        }
        var s = 0
        var e = n - 1
        var minLeft = arr[s]
        var minRight = arr[e]
        var minSum = 2_000_000_000
        while (s < e) {
            val sum = (arr[s] + arr[e]).toAbs()
            if (sum < minSum) {
                minSum = sum
                minLeft = arr[s]
                minRight = arr[e]
                if (minSum == 0) break
            }
            if ((arr[s + 1] + arr[e]).toAbs() > (arr[s] + arr[e - 1]).toAbs()) {
                e--
            } else {
                s++
            }
        }
        print("$minLeft $minRight")
    }

    private fun Int.toAbs(): Int {
        return if (this < 0) -this
        else this
    }
}

fun main() {
    `2467`().solution()
}