package boj.greedy

class `2847` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = IntArray(n + 1)
        for (i in 1 .. n) arr[i] = readLine().toInt()
        var answer = 0
        for (i in n - 1 downTo 1) {
            if (arr[i] >= arr[i + 1]) {
                answer += arr[i] - arr[i + 1] + 1
                arr[i] = arr[i + 1] - 1
            }
        }
        println(answer)
    }
}

fun main() {
    `2847`().solution()
}