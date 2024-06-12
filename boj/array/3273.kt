package boj.array

import java.util.StringTokenizer

class `3273` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        val x = readLine().toInt()
        val match = BooleanArray(x + 1)
        var matchCnt = 0
        for (num in arr) {
            if (x >= num) {
                if (match[num]) matchCnt++
                match[x - num] = true
            }
        }
        print(matchCnt)
    }
}

fun main() {
    `3273`().solution()
}