package boj.dp

class `11726` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val d = IntArray(1001)
        d[1] = 1
        d[2] = 2
        for (i in 3 .. n) d[i] = (d[i - 1] + d[i - 2]) % 10007
        println(d[n])
    }
}

fun main() {
    `11726`().solution()
}