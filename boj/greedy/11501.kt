package boj.greedy

import java.util.StringTokenizer

class `11501` {
    private val price = IntArray(1_000_001)

    fun solution() = with(System.`in`.bufferedReader()) {
        var t = readLine().toInt()
        val sb = StringBuilder()
        price[0] = 10000
        while (t-- > 0) {
            val n = readLine().toInt()
            val st = StringTokenizer(readLine())
            for (i in 1 .. n) {
                price[i] = st.nextToken().toInt()
            }
            var profit = 0L
            var high = price[n]
            for (i in n downTo 1) {
                if (price[i - 1] > high) {
                    high = price[i - 1]
                } else if (price[i - 1] < high) {
                    profit += high - price[i - 1]
                }
            }
            sb.append("$profit\n")
        }
        println(sb)
    }
}

fun main() {
    `11501`().solution()
}