package boj.greedy

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `30805` {
    private val indexA = Array<Queue<Int>>(101) { LinkedList() }
    private val indexB = Array<Queue<Int>>(101) { LinkedList() }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st = StringTokenizer(readLine())
        repeat(n) { idx ->
            val a = st.nextToken().toInt()
            indexA[a] += idx
        }
        val m = readLine().toInt()
        st = StringTokenizer(readLine())
        repeat(m) { idx ->
            val b = st.nextToken().toInt()
            indexB[b] += idx
        }
        val result = mutableListOf<Int>()
        var curA = -1
        var curB = -1
        for (i in 100 downTo 0) {
            while (indexA[i].isNotEmpty() && indexA[i].peek() < curA) {
                indexA[i].poll()
            }
            while (indexB[i].isNotEmpty() && indexB[i].peek() < curB) {
                indexB[i].poll()
            }
            while (indexA[i].isNotEmpty() && indexB[i].isNotEmpty()) {
                curA = indexA[i].poll()
                curB = indexB[i].poll()
                result += i
            }
        }
        println(result.size)
        println(result.joinToString(" "))
    }
}

fun main() {
    `30805`().solution()
}