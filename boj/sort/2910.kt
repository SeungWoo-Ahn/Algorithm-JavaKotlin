package boj.sort

import java.util.StringTokenizer

class `2910` {

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, c) = readLine().split(" ").map { it.toInt() }
        val arr = mutableListOf<Pair<Int, Int>>()
        val st = StringTokenizer(readLine())
        repeat(n) {
            val num = st.nextToken().toInt()
            var check = false
            for ((idx, pair) in arr.withIndex()) {
                if (pair.second == num) {
                    check = true
                    arr[idx] = Pair(arr[idx].first + 1, num)
                    break
                }
            }
            if (!check) arr.add(Pair(1, num))
        }
        arr.sortByDescending { it.first }
        val sb = StringBuilder()
        for (pair in arr) {
            repeat(pair.first) { sb.append("${pair.second} ") }
        }
        println(sb)
    }
}

fun main() {
    `2910`().solution()
}