package boj.math

class `2553` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var fiveCnt = 0
        var targetN = n
        while (targetN > 0) {
            fiveCnt += targetN / 5
            targetN /= 5
        }
        var ret = 1
        for (num in 1..n) {
            var targetNum = num
            while (targetNum % 5 == 0) {
                targetNum /= 5
            }
            if (fiveCnt > 0 && targetNum % 2 == 0) {
                targetNum /= 2
                fiveCnt--
            }
            ret = ret * targetNum % 10
        }
        print(ret)
    }
}

fun main() {
    `2553`().solution()
}