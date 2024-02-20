package boj.sort

class `11652` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = LongArray(n)
        repeat(n) { arr[it] = readLine().toLong() }
        arr.sort()
        var answer = -(1L shl 62) - 1
        var maxCnt = 0
        var cnt = 0
        for (i in arr.indices) {
            if (i == 0 || arr[i] == arr[i - 1]) cnt++
            else {
                if (cnt > maxCnt) {
                    maxCnt = cnt
                    answer = arr[i - 1]
                }
                cnt = 1
            }
        }
        if (cnt > maxCnt) answer = arr[n - 1]
        println(answer)
    }
}

fun main() {
    `11652`().solution()
}