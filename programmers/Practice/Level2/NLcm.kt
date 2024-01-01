package programmers.Practice.Level2

class NLcm {
    fun solution(arr: IntArray): Int {
        var answer = 1
        while (true) {
            val isLcm = isLcm(arr, answer)
            if (isLcm) break
            else answer++
        }
        return answer
    }

    private fun isLcm(arr: IntArray, predict: Int): Boolean {
        for (index in arr.indices) {
            if (predict % arr[index].toDouble() != 0.0) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val arr = intArrayOf(2,6,8,14)
    println(NLcm().solution(arr))
}