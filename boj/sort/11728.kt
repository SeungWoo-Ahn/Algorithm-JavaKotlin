package boj.sort

import java.util.StringTokenizer

class `11728` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st = StringTokenizer(readLine())
        val arrA = IntArray(n) { st.nextToken().toInt() }
        st = StringTokenizer(readLine())
        val arrB = IntArray(m) { st.nextToken().toInt() }

        val mergedArr = IntArray(n + m)
        var aIdx = 0
        var bIdx = 0
        for (i in mergedArr.indices) {
            if (aIdx == arrA.size) {
                mergedArr[i] = arrB[bIdx]
                bIdx++
                continue
            }
            if (bIdx == arrB.size) {
                mergedArr[i] = arrA[aIdx]
                aIdx++
                continue
            }
            if (arrA[aIdx] > arrB[bIdx]) {
                mergedArr[i] = arrB[bIdx]
                bIdx++
            } else {
                mergedArr[i] = arrA[aIdx]
                aIdx++
            }
        }

        println(mergedArr.joinToString(" "))
    }
}

fun main() {
    `11728`().solution()
}