package boj.stack

import java.util.StringTokenizer

class `22942` {
    private data class Circle(val st: Int, val en: Int): Comparable<Circle> {
        override fun compareTo(other: Circle): Int {
            return if (st != other.st) st - other.st
            else en - other.en
        }
    }

    private lateinit var circleList: Array<Circle>

    private fun getResult(): String {
        var isValid = true
        val endStack = ArrayDeque<Int>()
        for (circle in circleList) {
            while (endStack.isNotEmpty() && endStack.last() < circle.st) {
                endStack.removeLast()
            }
            if (endStack.isNotEmpty() && circle.st <= endStack.last() && endStack.last() <= circle.en) {
                isValid = false
                break
            }
            endStack.addLast(circle.en)
        }
        return if (isValid) "YES" else "NO"
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        circleList = Array(n) {
            st = StringTokenizer(readLine(), " ")
            val x = st.nextToken().toInt()
            val r = st.nextToken().toInt()
            Circle(x - r, x + r)
        }.apply { sort() }
    }

    fun solution() {
        input()
        print(getResult())
    }
}

fun main() {
    `22942`().solution()
}