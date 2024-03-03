package programmers.Heap

import java.util.PriorityQueue

class DiskController {
    fun solution(jobs: Array<IntArray>): Int {
        val jobQ = PriorityQueue<Pair<Int, Int>> { o1, o2 -> // 요청 시간 작은순 -> 작업 시간 작은순
            if (o1.first != o2.first) o1.first - o2.first
            else o1.second - o2.second
        }
        for (job in jobs) {
            jobQ.add(Pair(job[0], job[1]))
        }
        val stageJobQ = PriorityQueue<Pair<Int, Int>> { o1, o2 -> // 작업 시간 작은순 -> 요청 시간 큰순
            if (o1.second != o2.second) o1.second - o2.second
            else o2.first - o1.first
        }
        val front = jobQ.poll()
        var time = front.first + front.second
        var answer = front.second
        while (jobQ.isNotEmpty() || stageJobQ.isNotEmpty()) {
            while (jobQ.isNotEmpty() && jobQ.peek().first <= time) {
                stageJobQ.add(jobQ.poll())
            }
            if (stageJobQ.isNotEmpty()) {
                val stageFirst = stageJobQ.poll()
                time += stageFirst.second
                answer += time - stageFirst.first
            } else if (jobQ.isNotEmpty()) {
                val job = jobQ.poll()
                time = job.first + job.second
                answer += time - job.first
            }
        }
        return answer / jobs.size
    }
}

fun main() {
    val jobs = arrayOf(
        intArrayOf(0, 3),
        intArrayOf(1, 5),
        intArrayOf(2, 4),
        intArrayOf(5, 1)
    )
    println(DiskController().solution(jobs))
}