package boj.tree

import java.util.StringTokenizer

class `9489` {
    fun solution() = with(System.`in`.bufferedReader()) {
        var st: StringTokenizer
        val sb = StringBuilder()
        while (true) {
            st = StringTokenizer(readLine(), "  ")
            val n = st.nextToken().toInt()
            val k = st.nextToken().toInt()
            if (n == 0 && k == 0) break
            val arr = IntArray(n + 1)
            val parent = IntArray(n + 1)
            var idx = -1
            var targetIdx = 0
            arr[0] = -1
            parent[0] = -1
            st = StringTokenizer(readLine(), " ")
            for (i in 1..n) {
                arr[i] = st.nextToken().toInt()
                if (arr[i] == k) targetIdx = i
                if (arr[i] != arr[i - 1] + 1) idx++
                parent[i] = idx
            }
            var cousinCnt = 0
            for (i in 1..n) {
                if (parent[i] != parent[targetIdx] && parent[parent[i]] == parent[parent[targetIdx]]) {
                    cousinCnt++
                }
            }
            sb.appendLine(cousinCnt)
        }
        print(sb)
    }
}

fun main() {
    `9489`().solution()
}