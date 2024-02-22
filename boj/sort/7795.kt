package boj.sort

import java.util.StringTokenizer

class `7795` {
    data class Life(val length: Int, val group: Int = 0): Comparable<Life> {
        override fun compareTo(other: Life): Int {
            return if (length != other.length) length - other.length
            else group - other.group
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        var t = readLine().toInt()
        val sb = StringBuilder()
        while (t-- > 0) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val arr = Array(n + m) { Life(0) }
            var st = StringTokenizer(readLine())
            for (i in 0 until n) {
                arr[i] = Life(st.nextToken().toInt())
            }
            st = StringTokenizer(readLine())
            for (i in n until n + m) {
                arr[i] = Life(st.nextToken().toInt(), 1)
            }
            arr.sort()
            var cnt = 0
            var answer = 0
            for (i in arr.indices) {
                if (arr[i].group == 0) answer += cnt
                else cnt++
            }
            sb.append("$answer\n")
        }
        println(sb)
    }
}

fun main() {
    `7795`().solution()
}

