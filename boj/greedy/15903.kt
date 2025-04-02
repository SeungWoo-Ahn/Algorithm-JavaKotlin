package boj.greedy

import java.util.PriorityQueue
import java.util.StringTokenizer

class `15903` {
    private var m = 0
    private val pq = PriorityQueue<Long>()

    private fun play() {
        while (pq.size > 1 && m > 0) {
            val z = pq.poll() + pq.poll()
            repeat(2) { pq += z }
            m--
        }
    }

    private fun getScore(): Long {
        val cardList = pq.toList()
        return cardList.sum()
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        st = StringTokenizer(readLine(), " ")
        repeat(n) { pq += st.nextToken().toLong() }
    }

    fun solution() {
        input()
        play()
        print(getScore())
    }
}

fun main() {
    `15903`().solution()
}