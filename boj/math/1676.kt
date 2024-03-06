package boj.math

class `1676` {
    fun solution() = with(System.`in`.bufferedReader()) {
        var n = readLine().toInt()
        var answer = 0
        while (n != 0) {
            answer += n / 5
            n /= 5
        }
        println(answer)
    }
}

fun main() {
    `1676`().solution()
}