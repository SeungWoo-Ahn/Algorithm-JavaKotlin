package boj.math

import java.util.StringTokenizer

class `1978` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        val max = arr.max()
        val isPrime = BooleanArray(max + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        for (i in 2 .. max) {
            if (!isPrime[i]) continue
            for (j in i * i .. max step i) {
                if (j > max) break
                isPrime[j] = false
            }
        }
        val primeCnt = arr.count { isPrime[it] }
        println(primeCnt)
    }
}

fun main() {
    `1978`().solution()
}