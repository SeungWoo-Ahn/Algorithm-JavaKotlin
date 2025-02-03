package boj.bitmasking

import java.util.StringTokenizer
import kotlin.math.abs

class `2961` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        val taste = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(2) { st.nextToken().toInt() }
        }
        var result = Int.MAX_VALUE
        for (bit in 1 until (1 shl n)) {
            var mul = 1
            var sum = 0
            for (i in taste.indices) {
                if (((bit shr i) and 1) == 1) {
                    mul *= taste[i][0]
                    sum += taste[i][1]
                }
            }
            val diff = abs(mul - sum)
            if (diff < result) {
                result = diff
            }
        }
        print(result)
    }
}

fun main() {
    `2961`().solution()
}