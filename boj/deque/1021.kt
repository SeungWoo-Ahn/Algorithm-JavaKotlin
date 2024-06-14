package boj.deque

import java.util.StringTokenizer

class `1021` {
    private val q = ArrayDeque<Int>()
    private var calcTime = 0

    private fun calc1() {
        q.removeFirst()
    }

    private fun calc2() {
        q.addLast(q.removeFirst())
        calcTime++
    }

    private fun calc3() {
        q.addFirst(q.removeLast())
        calcTime++
    }

    private fun findTarget(target: Int) {
        val rightDis = q.indexOf(target)
        val leftDis = q.size - rightDis
        val isRightDir = rightDis < leftDis
        while (q.first() != target) {
            if (isRightDir) calc2()
            else calc3()
        }
        calc1()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        (1 .. n).forEach(q::add)
        repeat(m) {
            val target = st.nextToken().toInt()
            findTarget(target)
        }
        print(calcTime)
    }
}

fun main() {
    `1021`().solution()
}