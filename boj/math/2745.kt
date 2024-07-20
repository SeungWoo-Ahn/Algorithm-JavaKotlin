package boj.math

class `2745` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, bStr) = readLine().split(" ")
        val b = bStr.toInt()
        var temp = 1
        var sum = 0
        for (i in n.length - 1 downTo 0) {
            val char = n[i]
            sum += if (char in 'A'..'Z') {
                (char - 'A' + 10) * temp
            } else {
                (char - '0') * temp
            }
            temp *= b
        }
        print(sum)
    }
}

fun main() {
    `2745`().solution()
}