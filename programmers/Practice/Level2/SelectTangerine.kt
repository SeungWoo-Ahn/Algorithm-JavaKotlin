package programmers.Practice.Level2

import java.util.Arrays

class SelectTangerine {
    fun solution(k: Int, tangerine: IntArray): Int {
        var answer = 0
        val maxSize = tangerine.maxOf { it }
        val t = IntArray(maxSize+1)
        for (i in tangerine.indices) {
            t[tangerine[i]]++
        }
        Arrays.sort(t)
        var total = 0
        for (i in t.size - 1 downTo 0) {
            total += t[i]
            answer++
            if (total >= k) break
        }
        return answer
    }
}

fun main() {
    val k = 6
    val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)
    println(SelectTangerine().solution(k, tangerine))
}