package boj.math

import java.util.StringTokenizer

class `27172` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        val exist = BooleanArray(SIZE)
        val cards = IntArray(SIZE)
        repeat(n) { idx ->
            val num = st.nextToken().toInt()
            exist[num] = true
            cards[idx] = num
        }
        val v = Array(SIZE) { mutableListOf<Int>() }
        for (i in 0 until n) {
            for (j in 1 until cards[i]) {
                if (j * j > cards[i]) break
                if (cards[i] % j == 0) v[i].add(j)
                if (j * j != cards[i] && cards[i] % j == 0) v[i].add(cards[i] / j)
            }
        }
        val score = IntArray(SIZE)
        for (i in 0 until n) {
            for (j in v[i]) {
                if (exist[j]) {
                    score[j]++
                    score[cards[i]]--
                }
            }
        }
        val sb = StringBuilder()
        for (i in 0 until n) {
            sb.append("${score[cards[i]]} ")
        }
        print(sb)
    }

    companion object {
        private const val SIZE = 1_000_001
    }
}

fun main() {
    `27172`().solution()
}