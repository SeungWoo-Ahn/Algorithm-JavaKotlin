package boj.backtracking

class `9663` {
    private var n = 0
    private var cnt = 0
    private val v1 = BooleanArray(40)
    private val v2 = BooleanArray(40)
    private val v3 = BooleanArray(40)

    private fun backtracking(depth: Int) {
        if (depth == n) {
            cnt++
            return
        }
        for (i in 0 until n) {
            if (v1[i] || v2[i + depth] || v3[depth - i + n - 1]) continue
            v1[i] = true
            v2[i + depth] = true
            v3[depth - i + n - 1] = true
            backtracking(depth + 1)
            v1[i] = false
            v2[i + depth] = false
            v3[depth - i + n - 1] = false
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()
        backtracking(0)
        println(cnt)
    }
}

fun main() {
    `9663`().solution()
}