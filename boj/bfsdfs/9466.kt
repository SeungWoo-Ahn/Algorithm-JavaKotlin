package boj.bfsdfs

import java.util.StringTokenizer

class `9466` {
    private var table = intArrayOf()
    private var state = intArrayOf()

    private fun calcSoloCnt(): Int {
        for (i in table.indices) {
            if (state[i] == NOT_VISITED) check(i)
        }
        return state.count { it != IS_TEAM }
    }

    private fun check(idx: Int) {
        var cur = idx
        while (true) {
            state[cur] = idx
            cur = table[cur]
            if (state[cur] == idx) {
                while (state[cur] != IS_TEAM) {
                    state[cur] = IS_TEAM
                    cur = table[cur]
                }
                return
            }
            else if (state[cur] != NOT_VISITED) return
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        var st: StringTokenizer
        val sb = StringBuilder()
        repeat(t) {
            val n = readLine().toInt()
            st = StringTokenizer(readLine())
            table = IntArray(n) { st.nextToken().toInt() - 1 }
            state = IntArray(n) { NOT_VISITED }
            sb.appendLine(calcSoloCnt())
        }
        print(sb)
    }

    companion object {
        private const val NOT_VISITED = -1
        private const val IS_TEAM = -2
    }
}

fun main() {
    `9466`().solution()
}