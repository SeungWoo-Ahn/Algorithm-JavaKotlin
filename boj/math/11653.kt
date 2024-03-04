package boj.math

class `11653` {
    fun solution() = with(System.`in`.bufferedReader()) {
        var n = readLine().toInt()
        val sb = StringBuilder()
        for (i in 2 .. n) {
            if (i * i > n) break
            while (n % i == 0) {
                sb.append("$i\n")
                n /= i
            }
        }
        if (n != 1) sb.append(n)
        println(sb)
    }
}

fun main() {
    `11653`().solution()
}