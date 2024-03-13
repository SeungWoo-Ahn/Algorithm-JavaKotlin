package boj.two_pointer

import java.util.StringTokenizer

class `13144` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() }
        val table = BooleanArray(100_001)
        table[arr[0]] = true
        var e = 0
        var answer = 0L
        for (s in arr.indices) {
            while (e < n - 1 && !table[arr[e + 1]]) {
                e++
                table[arr[e]] = true
            }
            answer += e - s + 1
            table[arr[s]] = false
        }
        println(answer)
    }
}

fun main() {
    `13144`().solution()
}