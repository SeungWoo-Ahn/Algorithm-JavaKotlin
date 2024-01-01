package programmers.Practice.Level1

class DotProduct {
    fun solution(a: IntArray, b: IntArray): Int {
        var answer = 0
        for(i in a.indices) {
            answer += a[i] * b[i]
        }
        return answer
    }
}

fun main() {
    println(DotProduct().solution(a = intArrayOf(1,2,3,4), b = intArrayOf(-3,-1,0,2)))
}