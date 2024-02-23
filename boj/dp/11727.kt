package boj.dp

class `11727` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val d = IntArray(1001)
        d[1] = 1.also { d[2] = 3 }
        for (i in 3 .. n) {
            d[i] = (d[i - 1] + d[i - 2] * 2) % 10007
        }
        print(d[n])
    }
}

fun main() {
    `11727`().solution()
}