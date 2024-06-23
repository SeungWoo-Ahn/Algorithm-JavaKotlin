package boj.queue

class `11866` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val q = ArrayDeque<Int>()
        val arr = mutableListOf<Int>()
        (1 .. n).forEach { q += it }
        while (q.isNotEmpty()) {
            repeat(k - 1) { q += q.removeFirst() }
            arr += q.removeFirst()
        }
        print("<${arr.joinToString(", ")}>")
    }
}

fun main() {
    `11866`().solution()
}