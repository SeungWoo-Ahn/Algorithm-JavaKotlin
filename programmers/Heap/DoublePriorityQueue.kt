package programmers.Heap

import java.util.Collections
import java.util.PriorityQueue

class DoublePriorityQueue {
    fun solution(operations: Array<String>): IntArray {
        val maxQ = PriorityQueue<Int>(Collections.reverseOrder())
        val minQ = PriorityQueue<Int>()
        operations.forEach { operation ->
            val (command, data) = operation.split(" ")
            when (command) {
                "I" -> {
                    maxQ.add(data.toInt())
                    minQ.add(data.toInt())
                }
                "D" -> {
                    if (data == "1" && maxQ.isNotEmpty()) {
                        minQ.remove(maxQ.poll())
                    } else if (data == "-1" && minQ.isNotEmpty()) {
                        maxQ.remove(minQ.poll())
                    }
                }
            }
        }
        if (maxQ.isEmpty()) return intArrayOf(0, 0)
        return intArrayOf(maxQ.poll(), minQ.poll())
    }
}

fun main() {
    val operations = arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333")
    println(DoublePriorityQueue().solution(operations).joinToString())
}