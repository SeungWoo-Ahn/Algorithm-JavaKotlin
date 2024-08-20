package programmers.Practice.Level3

class PopTheBalloon {
    fun solution(a: IntArray): Int {
        val n = a.size
        if (n <= 2) {
            return n
        }
        val minFromLeft = IntArray(n)
        val minFromRight = IntArray(n)
        minFromLeft[0] = a[0]
        minFromRight[n - 1] = a[n - 1]
        for (i in 1 until n) {
            minFromLeft[i] = minOf(minFromLeft[i - 1], a[i])
        }
        for (i in n - 2 downTo 0) {
            minFromRight[i] = minOf(minFromRight[i + 1], a[i])
        }
        var answer = 2
        for (i in 1 until n - 1) {
            var cnt = 0
            if (a[i] > minFromLeft[i - 1]) cnt++
            if (a[i] > minFromRight[i + 1]) cnt++
            if (cnt < 2) answer++
        }
        return answer
    }
}

fun main() {
    val a = intArrayOf(-16,27,65,-2,58,-92,-71,-68,-61,-33)
    val answer = PopTheBalloon().solution(a)
    print(answer)
}