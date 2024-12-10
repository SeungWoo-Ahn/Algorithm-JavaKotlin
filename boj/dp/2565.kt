package boj.dp

class `2565` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val arr = IntArray(501)
        val length = IntArray(501)
        val n = readLine().toInt()
        repeat(n) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            arr[a] = b
        }
        var result = 0
        for (i in arr.indices) {
            if (arr[i] == 0) continue
            length[i] = 1
            for (j in 0 until i) {
                if (arr[i] > arr[j] && length[i] < length[j] + 1) {
                    length[i] = length[j] + 1
                }
                if (length[i] > result) {
                    result = length[i]
                }
            }
        }
        print(n - result)
    }
}

fun main() {
    `2565`().solution()
}