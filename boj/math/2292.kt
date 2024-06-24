package boj.math

class `2292` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var count = 1
        var layer = 1
        while (count < n) {
            count += layer * 6
            layer++
        }
        println(layer)
    }
}

fun main() {
    `2292`().solution()
}