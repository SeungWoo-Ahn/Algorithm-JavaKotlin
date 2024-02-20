package boj.sort

import java.util.PriorityQueue

class `1181` {
    data class Word(val word: String): Comparable<Word> {
        override fun compareTo(other: Word): Int {
            return if (word.length != other.word.length) word.length.compareTo(other.word.length)
            else word.compareTo(other.word)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val set = HashSet<String>()
        repeat(n) { set.add(readLine()) }
        val pq = PriorityQueue<Word>()
        set.forEach { pq.add(Word(it)) }
        val sb = StringBuilder()
        while (pq.isNotEmpty()) { sb.append("${pq.poll().word}\n") }
        println(sb)
    }
}

fun main() {
    `1181`().solution()
}