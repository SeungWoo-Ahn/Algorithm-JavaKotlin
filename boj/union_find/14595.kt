package boj.union_find

import java.util.StringTokenizer

class `14595` {
    data class Action(val st: Int, val en: Int) : Comparable<Action> {
        override fun compareTo(other: Action): Int {
            return if (st != other.st) st - other.st
            else other.en - en
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        if (m == 0) {
            print(n)
            return
        }
        var st: StringTokenizer
        val actions = Array(m) {
            st = StringTokenizer(readLine())
            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()
            Action(x, y)
        }
        actions.sort()
        var (curSt, curEn) = actions.first()
        var groupCnt = n
        for (i in 1 until actions.size) {
            val action = actions[i]
            if (action.st == curSt || action.en <= curEn) continue
            if (action.st <= curEn) {
                curEn = action.en
            } else {
                groupCnt -= (curEn - curSt)
                curSt = action.st
                curEn = action.en
            }
        }
        groupCnt -= (curEn - curSt)
        print(groupCnt)
    }
}

fun main() {
    `14595`().solution()
}