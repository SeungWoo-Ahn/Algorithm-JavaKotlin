package boj.sort

class `11656` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val size = s.length
        val arr = Array(size) { "" }
        for (i in 0 until size) {
            arr[i] = s.substring(i until  size)
        }
        arr.sort()
        val sb = StringBuilder()
        arr.forEach { sb.append("$it\n") }
        println(sb)
    }
}

fun main() {
    `11656`().solution()
}