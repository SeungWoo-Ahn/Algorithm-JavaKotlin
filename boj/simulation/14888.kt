package boj.simulation

import java.util.StringTokenizer

class `14888` {
    private var nums = intArrayOf()
    private var operators = arrayListOf<Int>()
    private var arr = intArrayOf()
    private var visited = booleanArrayOf()
    private var max = Int.MIN_VALUE
    private var min = Int.MAX_VALUE

    private fun backtracking(depth: Int) {
        if (depth == nums.size - 1) {
            calculate()
            return
        }
        var temp = -1
        for (i in operators.indices) {
            if (!visited[i] && operators[i] != temp) {
                visited[i] = true
                arr[depth] = operators[i]
                temp = operators[i]
                backtracking(depth + 1)
                visited[i] = false
            }
        }
    }

    private fun calculate() {
        var result = nums[0]
        for (i in 1 until nums.size) {
            when (arr[i - 1]) {
                0 -> result += nums[i]
                1 -> result -= nums[i]
                2 -> result *= nums[i]
                3 -> if (result < 0) {
                    result = -((-result) / nums[i])
                } else {
                    result /= nums[i]
                }
            }
        }
        if (result > max) max = result
        if (result < min) min = result
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st = StringTokenizer(readLine())
        nums = IntArray(n) { st.nextToken().toInt() }
        st = StringTokenizer(readLine())
        val operatorCnt = IntArray(4) { st.nextToken().toInt() }
        for (i in 0 until 4) {
            repeat(operatorCnt[i]) { operators.add(i) }
        }
        arr = IntArray(n - 1)
        visited = BooleanArray(n - 1)
        backtracking(0)
        println("$max\n$min")
    }
}

fun main() {
    `14888`().solution()
}