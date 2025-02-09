package boj.stack

import java.util.StringTokenizer

class `17298` {
    private lateinit var arr: IntArray

    private fun input() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        arr = IntArray(n) { st.nextToken().toInt() }
    }

    fun solution() {
        input()
        val stack = ArrayDeque<Int>()
        for (i in arr.indices) {
            while (stack.isNotEmpty() && arr[stack.last()] < arr[i]) {
                arr[stack.removeLast()] = arr[i]
            }
            stack.addLast(i)
        }
        while (stack.isNotEmpty()) {
            arr[stack.removeLast()] = -1
        }
        print(arr.joinToString(" "))
    }
}

fun main() {
    `17298`().solution()
}