package boj.greedy

import java.util.PriorityQueue
import java.util.StringTokenizer

class `11000` {
    private data class Info(val s: Int, val t: Int) : Comparable<Info> {
        override fun compareTo(other: Info): Int {
            return if (s != other.s) s - other.s
            else t - other.t
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        val infoList = Array(n) {
            st = StringTokenizer(readLine(), " ")
            Info(st.nextToken().toInt(), st.nextToken().toInt())
        }.apply { sort() }
        val endTimePq = PriorityQueue<Int>()
        for (info in infoList) {
            if (endTimePq.isNotEmpty() && endTimePq.peek() <= info.s) {
                endTimePq.poll()
            }
            endTimePq += info.t
        }
        print(endTimePq.size)
    }
}

fun main() {
    `11000`().solution()
}