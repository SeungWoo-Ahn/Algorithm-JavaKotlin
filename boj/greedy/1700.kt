package boj.greedy

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `1700` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val scheduler = Array<Queue<Int>>(k + 1) { LinkedList() }
        val q = LinkedList<Int>() as Queue<Int>
        val st = StringTokenizer(readLine())
        repeat(k) { idx ->
            st.nextToken().toInt().let {
                q.offer(it)
                scheduler[it].offer(idx + 1)
            }
        }
        val multiTab = arrayListOf<Int>()
        var answer = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (multiTab.size < n && !multiTab.contains(cur)) {
                multiTab.add(cur)
            } else if (!multiTab.contains(cur)) {
                var maxIdx = 0
                var maxNum = 0
                for (i in multiTab.indices) {
                    if (scheduler[multiTab[i]].isEmpty()) {
                        maxNum = multiTab[i]
                        break
                    } else if (scheduler[multiTab[i]].peek() > maxIdx) {
                        maxIdx = scheduler[multiTab[i]].peek()
                        maxNum = multiTab[i]
                    }
                }
                multiTab.remove(maxNum)
                multiTab.add(cur)
                answer++
            }
            scheduler[cur].poll()
        }
        println(answer)
    }
}

fun main() {
    `1700`().solution()
}