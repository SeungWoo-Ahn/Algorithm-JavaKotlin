package boj.math

class `1747` {
    private fun isPrime(num: Int): Boolean {
        if (num < 2) {
            return false
        }
        for (i in 2..num) {
            if (i * i > num) break
            if (num % i == 0) {
                return false
            }
        }
        return true
    }

    private fun isPall(num: Int): Boolean {
        val numStr = num.toString()
        return if (numStr.length % 2 == 0) isPallEven(numStr)
        else isPallOdd(numStr)
    }

    private fun isPallOdd(numStr: String): Boolean {
        val mid = numStr.length / 2
        for (dis in 1..mid) {
            if (numStr[mid - dis] != numStr[mid + dis]) {
                return false
            }
        }
        return true
    }

    private fun isPallEven(numStr: String): Boolean {
        var left = numStr.length / 2 - 1
        var right = numStr.length / 2
        while (left >= 0) {
           if (numStr[left] != numStr[right]) {
               return false
           }
            left--
            right++
        }
        return true
    }

    private fun getResult(n: Int): Int {
        var num = n
        while (true) {
            if (isPrime(num) && isPall(num)) {
                return num
            }
            num++
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        print(getResult(n))
    }
}

fun main() {
    `1747`().solution()
}