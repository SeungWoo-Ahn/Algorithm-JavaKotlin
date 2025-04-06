package boj.binary_search

import java.util.StringTokenizer

class `2696` {
    private fun lowerBound(num: Int, arr: List<Int>): Int {
        var st = 0
        var en = arr.size
        while (st < en) {
            val mid = (st + en) / 2
            if (arr[mid] < num) st = mid + 1
            else en = mid
        }
        return st
    }

    private fun getMid(arr: List<Int>): Int {
        val idx = arr.size shr 1
        return arr[idx]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val m = readLine().toInt()
            val arr = mutableListOf<Int>()
            val result = mutableListOf<Int>()
            var cnt = 0
            var st = StringTokenizer(readLine(), " ")
            while (cnt < m) {
                cnt++
                val num = st.nextToken().toInt()
                val lowerIdx = lowerBound(num, arr)
                arr.add(lowerIdx, num)
                if (cnt % 2 == 1) {
                    result += getMid(arr)
                }
                if (cnt % 10 == 0) {
                    st = StringTokenizer(readLine(), " ")
                }
            }
            sb.appendLine(result.size)
            for (i in result.indices) {
                sb.append(result[i])
                if ((i + 1) % 10 == 0) sb.append('\n')
                else sb.append(' ')
            }
            sb.append('\n')
        }
        print(sb)
    }
}

fun main() {
    `2696`().solution()
}