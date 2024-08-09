package programmers.Greedy

class NumberGame {
    fun solution(a: IntArray, b: IntArray): Int {
        a.sortDescending()
        b.sortDescending()
        val dq = ArrayDeque<Int>()
        b.forEach { dq += it }
        var answer = 0
        for (i in a.indices) {
            if (a[i] >= dq[0]) {
                dq.removeLast()
            } else {
                dq.removeFirst()
                answer++
            }
        }
        return answer
    }
}

fun main() {
    val a = intArrayOf(5, 1, 3, 7)
    val b = intArrayOf(2, 2, 6, 8)
    val answer = NumberGame().solution(a, b)
    print(answer)
}