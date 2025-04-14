package boj.math

import java.util.StringTokenizer

class `5618` {
    private fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    private fun getTotalGcd(arr: IntArray): Int {
        for (i in 1 until arr.size) {
            val a = arr[i - 1]
            val b = arr[i]
            val gcd = gcd(a, b)
            arr[i] = gcd
        }
        return arr.last()
    }

    private fun getResult(gcd: Int): String {
        val front = ArrayDeque<Int>()
        val back = ArrayDeque<Int>()
        for (num in 1..gcd) {
            if (num * num > gcd) break
            if (gcd % num == 0) {
                front += num
                if (num * num != gcd) {
                    back += gcd / num
                }
            }
        }
        val sb = StringBuilder()
        while (front.isNotEmpty()) sb.appendLine(front.removeFirst())
        while (back.isNotEmpty()) sb.appendLine(back.removeLast())
        return sb.toString()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine(), " ")
        val arr = IntArray(n) { st.nextToken().toInt() }
        val totalGcd = getTotalGcd(arr)
        val result = getResult(totalGcd)
        print(result)
    }
}

fun main() {
    `5618`().solution()
}