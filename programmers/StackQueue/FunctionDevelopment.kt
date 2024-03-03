package programmers.StackQueue

import java.util.LinkedList
import java.util.Queue

class FunctionDevelopment {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        for (i in progresses.indices) {
            q.offer(Pair(progresses[i], i))
        }
        val answer = arrayListOf<Int>()
        var day = 0
        var temp = 0
        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (cur.first + speeds[cur.second] * day >= 100) {
                temp++
            } else {
                answer.add(temp)
                val share = (100 - cur.first) / speeds[cur.second]
                val remain = (100 - cur.first) % speeds[cur.second]
                day = if (remain == 0) share else share + 1
                temp = 1
            }
        }
        answer.add(temp)
        answer.removeAt(0)
        return answer.toIntArray()
    }
}

fun main() {
    val progresses = intArrayOf(95, 90, 99, 99, 80, 99)
    val speeds = intArrayOf(1, 1, 1, 1, 1, 1)
    println(FunctionDevelopment().solution(progresses, speeds).joinToString())
}