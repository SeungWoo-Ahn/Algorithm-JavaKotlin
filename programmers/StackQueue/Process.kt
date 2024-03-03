package programmers.StackQueue

import java.util.LinkedList
import java.util.Queue

class Process {
    fun solution(priorities: IntArray, location: Int): Int {
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        var max = 0
        val pCnt = IntArray(10)
        for (i in priorities.indices) {
            priorities[i].let {
                q.offer(Pair(it, i))
                pCnt[it]++
                if (it > max) {
                    max = it
                }
            }
        }
        var answer = 0
        while (true) {
            val cur = q.poll()
            if (cur.first < max) q.offer(cur)
            else {
                answer++
                if (cur.second == location) break
                if (--pCnt[cur.first] == 0) {
                    for (i in cur.first - 1 downTo 1) {
                        if (pCnt[i] > 0) {
                            max = i
                            break
                        }
                    }
                }
            }
        }
        return answer
    }
}

fun main() {
    val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
    val location = 0
    println(Process().solution(priorities, location))
}