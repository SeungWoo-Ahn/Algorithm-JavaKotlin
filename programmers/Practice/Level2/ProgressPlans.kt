package programmers.Practice.Level2

import java.util.PriorityQueue

class ProgressPlans {
    private data class Plan(val name: String, val start: Int, val playTime: Int) : Comparable<Plan> {
        override fun compareTo(other: Plan): Int {
            return start - other.start
        }
    }

    private fun String.toMinute(): Int {
        val (hh, mm) = split(":").map { it.toInt() }
        return hh * 60 + mm
    }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val mainQ = PriorityQueue<Plan>()
        val readyQ = ArrayDeque<Plan>()
        for ((name, start, playTime) in plans) {
            mainQ += Plan(name, start.toMinute(), playTime.toInt())
        }
        val result = mutableListOf<String>()
        while (mainQ.isNotEmpty()) {
            val (name, start, playTime) = mainQ.poll()
            if (mainQ.isEmpty()) {
                result += name
                break
            }
            val curEnd = start + playTime
            val nxtStart = mainQ.peek().start
            if (curEnd == nxtStart) {
                result += name
            } else if (curEnd < nxtStart) {
                result += name
                var freeTime = nxtStart - curEnd
                while (readyQ.isNotEmpty()) {
                    val readyPlan = readyQ.removeLast()
                    if (readyPlan.playTime <= freeTime) {
                        freeTime -= readyPlan.playTime
                        result += readyPlan.name
                        if (freeTime == 0) {
                            break
                        }
                    } else {
                        val restTime = readyPlan.playTime - freeTime
                        readyQ.addLast(readyPlan.copy(playTime = restTime))
                        break
                    }
                }
            } else {
                val restTime = curEnd - nxtStart
                readyQ.addLast(Plan(name, start, restTime))
            }
        }
        while (readyQ.isNotEmpty()) {
            result += readyQ.removeLast().name
        }
        return result.toTypedArray()
    }
}

fun main() {
    val plans = arrayOf(
        arrayOf("science", "12:40", "50"),
        arrayOf("music", "12:20", "40"),
        arrayOf("history", "14:00", "30"),
        arrayOf("computer", "12:30", "100")
    )
    val answer = ProgressPlans().solution(plans)
    print(answer.joinToString())
}