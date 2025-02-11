package boj.deque

import java.util.StringTokenizer

class `11003` {
    fun solution() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val n = st.nextToken().toInt()
        val l = st.nextToken().toInt()
        val dq = ArrayDeque<Int>()
        val arr = IntArray(n)
        val d = IntArray(n)
        st = StringTokenizer(readLine(), " ")
        for (i in d.indices) {
            arr[i] = st.nextToken().toInt()
            while (dq.isNotEmpty() && arr[dq.last()] > arr[i]) {
                dq.removeLast()
            }
            while (dq.isNotEmpty() && dq.first() <= i - l) {
                dq.removeFirst()
            }
            dq.addLast(i)
            d[i] = arr[dq.first()]
        }
        val bw = System.out.bufferedWriter()
        for (num in d) {
            bw.write("$num ")
        }
        bw.flush()
        bw.close()
    }
}

fun main() {
    `11003`().solution()
}