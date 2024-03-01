package boj.greedy

import java.util.Collections
import java.util.PriorityQueue

class `1744` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val plusQ = PriorityQueue<Int>(Collections.reverseOrder())
        val minusQ = PriorityQueue<Int>()
        var oneCnt = 0
        var hasZero = false
        repeat(n) {
            val num = readLine().toInt()
            if (num == 0) hasZero = true
            else if (num == 1) oneCnt++
            else if (num > 1) plusQ.add(num)
            else minusQ.add(num)
        }
        var answer = oneCnt
        var temp = 1
        var bindCnt = 0
        while (plusQ.isNotEmpty()) {
            val num = plusQ.poll()
            bindCnt++
            temp *= num
            if (bindCnt == 2) {
                answer += temp
                temp = 1
                bindCnt = 0
            }
        }
        if (bindCnt != 0) answer += temp
        temp = 1
        bindCnt = 0
        while (minusQ.isNotEmpty()) {
            val num = minusQ.poll()
            bindCnt++
            temp *= num
            if (bindCnt == 2) {
                answer += temp
                temp = 1
                bindCnt = 0
            }
        }
        if (!hasZero && bindCnt != 0) answer += temp
        println(answer)
    }
}

fun main() {
    `1744`().solution()
}