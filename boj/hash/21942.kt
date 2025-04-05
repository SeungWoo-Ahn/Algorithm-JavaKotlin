package boj.hash

import java.util.StringTokenizer
import java.util.TreeMap

class `21942` {
    private data class Note(val info: HashMap<String, Int>)

    private val daysOfMonth = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private lateinit var daysAcc: IntArray

    private fun setDaysAcc() {
        daysAcc = IntArray(daysOfMonth.size)
        for (month in 1 until daysAcc.size) {
            daysAcc[month] += daysAcc[month - 1] + daysOfMonth[month]
        }
    }

    private fun lToMinute(l: String): Int {
        val day = l.substring(0, 3).toInt()
        val time = l.substring(4)
        return dayToMinute(day) + timeToMinute(time)
    }

    private fun dateTimeToMinute(date: String, time: String): Int {
        val month = date.substring(5, 7).toInt()
        val day = date.substring(8).toInt()
        val totalDay = daysAcc[month - 1] + day
        return dayToMinute(totalDay) + timeToMinute(time)
    }

    private fun dayToMinute(day: Int): Int {
        return day * 24 * 60
    }

    private fun timeToMinute(time: String): Int {
        val hour = time.substring(0, 2).toInt()
        val minute = time.substring(3).toInt()
        return hour * 60 + minute
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val notes = hashMapOf<String, Note>()
        val fair = TreeMap<String, Long>()
        var st = StringTokenizer(readLine(), " ")
        val n = st.nextToken().toInt()
        val l = lToMinute(st.nextToken())
        val f = st.nextToken().toInt()
        setDaysAcc()
        repeat(n) {
            st = StringTokenizer(readLine(), " ")
            val minute = dateTimeToMinute(st.nextToken(), st.nextToken())
            val p = st.nextToken()
            val m = st.nextToken()
            if (notes.containsKey(p).not()) {
                notes[p] = Note(info = hashMapOf())
            }
            if (notes[p]!!.info.containsKey(m)) { // 반납
                val usedTime = minute - notes[p]!!.info[m]!!
                if (usedTime > l) {
                    fair[m] = fair.getOrDefault(m, 0L) + (usedTime - l) * f
                }
                notes[p]!!.info.remove(m)
            } else { // 대여
                notes[p]!!.info[m] = minute
            }
        }
        if (fair.isEmpty()) {
            print(-1)
        } else {
            val sb = StringBuilder()
            for (key in fair.keys) {
                sb.appendLine("$key ${fair[key]}")
            }
            print(sb)
        }
    }
}

fun main() {
    `21942`().solution()
}