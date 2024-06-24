package boj.queue

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `1966` {
    private val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
    private var countArr = intArrayOf()

    private fun testCase(m: Int, sMax: Int): Int {
        var order = 0
        var maxPriority = sMax
        while (q.isNotEmpty()) {
            val (priority, idx) = q.peek()
            if (priority == maxPriority) {
                order++
                if (idx == m) break
                q.poll()
                countArr[maxPriority]--
                while (maxPriority > 1 && countArr[maxPriority] == 0)
                    maxPriority--
            } else if (priority < maxPriority) {
                q += q.poll()
            }
        }
        return order
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        var st: StringTokenizer
        repeat(t) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            st = StringTokenizer(readLine())
            q.clear()
            countArr = IntArray(10)
            var sMax = 0
            repeat(n) { idx ->
                val priority = st.nextToken().toInt()
                sMax = maxOf(sMax, priority)
                countArr[priority]++
                q += priority to idx
            }
            sb.appendLine(testCase(m, sMax))
        }
        print(sb)
    }
}

fun main() {
    `1966`().solution()
}