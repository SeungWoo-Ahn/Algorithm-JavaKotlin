package boj.backtracking

import java.util.StringTokenizer

class `16987` {
    data class Egg(var s: Int, val w: Int)

    private var input = arrayOf<Egg>()
    private var breaked = booleanArrayOf()
    private var cnt = 0

    private fun backtracking(n: Int, depth: Int, value: Int) {
        if (depth == n || value == n -1) {
            if (value > cnt) cnt = value
            return
        }
        if (breaked[depth]) {
            backtracking(n, depth + 1, value)
            return
        }
        for (i in 0 until n) {
            if (breaked[i] || i == depth) continue
            input[depth].s -= input[i].w
            input[i].s -= input[depth].w
            if (input[depth].s <= 0 && input[i].s <= 0) {
                breaked[depth] = true
                breaked[i] = true
                backtracking(n, depth + 1, value + 2)
                breaked[depth] = false
                breaked[i] = false
            } else if (input[i].s <= 0) {
                breaked[i] = true
                backtracking(n, depth + 1, value + 1)
                breaked[i] = false
            } else if (input[depth].s <= 0) {
                breaked[depth] = true
                backtracking(n, depth + 1, value + 1)
                breaked[depth] = false
            } else {
                backtracking(n, depth + 1, value)
            }
            input[depth].s += input[i].w
            input[i].s += input[depth].w
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        input = Array(n) {
            val st = StringTokenizer(readLine())
            Egg(st.nextToken().toInt(), st.nextToken().toInt())
        }
        breaked = BooleanArray(n)
        backtracking(n, 0, 0)
        println(cnt)
    }
}

fun main() {
    `16987`().solution()
}