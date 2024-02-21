package boj.sort

import java.util.PriorityQueue
import java.util.StringTokenizer

class `10814` {
    data class User(val age: Int, val index: Int, val name: String): Comparable<User> {
        override fun compareTo(other: User): Int {
            return if (age != other.age) age - other.age
            else index - other.index
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val pq = PriorityQueue<User>()
        repeat(readLine().toInt()) {
            val st = StringTokenizer(readLine())
            pq.add(User(st.nextToken().toInt(), it, st.nextToken()))
        }
        val sb = StringBuilder()
        while (pq.isNotEmpty()) {
            pq.poll().let { sb.append("${it.age} ${it.name}\n") }
        }
        println(sb)
    }
}

fun main() {
    `10814`().solution()
}