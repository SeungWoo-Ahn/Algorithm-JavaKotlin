package boj.math

import java.util.StringTokenizer

class `9613` {
    private val nums = IntArray(101)
    private val arr = IntArray(2)
    private var answer = 0L

    private fun combination(n: Int, depth: Int = 0, startIdx: Int = 0) {
        if (depth == 2) {
            answer += gcd(nums[arr[0]], nums[arr[1]])
            return
        }
        for (i in startIdx until n) {
            arr[depth] = i
            combination(n, depth + 1, i + 1)
        }
    }

    private fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val st = StringTokenizer(readLine())
            val n = st.nextToken().toInt()
            for (i in 0 until  n) nums[i] = st.nextToken().toInt()
            answer = 0L
            combination(n)
            sb.append("$answer\n")
        }
        println(sb)
    }
}

fun main() {
    `9613`().solution()
}