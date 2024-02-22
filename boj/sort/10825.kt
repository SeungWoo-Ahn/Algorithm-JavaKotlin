package boj.sort

import java.util.PriorityQueue
import java.util.StringTokenizer

class `10825` {
    data class Student(val name: String, val korean: Int, val english: Int, val math: Int): Comparable<Student> {
        override fun compareTo(other: Student): Int {
            return if (korean != other.korean) other.korean - korean
            else if (english != other.english) english - other.english
            else if (math != other.math) other.math - math
            else name.compareTo(other.name)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()){
        val n =  readLine().toInt()
        val pq = PriorityQueue<Student>()
        repeat(n) {
            val st = StringTokenizer(readLine())
            pq.add(Student(st.nextToken(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt()))
        }
        val sb = StringBuilder()
        while (pq.isNotEmpty()) { sb.append("${pq.poll().name}\n") }
        println(sb)
    }
}

fun main() {
    `10825`().solution()
}