package programmers.Practice.Level2

class MatrixMultiplication {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val n = arr1.size
        val c = arr2.size
        val m = arr2[0].size
        return Array(n) { x ->
            IntArray(m) { y ->
                var sum = 0
                for (idx in 0 until c) {
                    sum += arr1[x][idx] * arr2[idx][y]
                }
                sum
            }
        }
    }
}

fun main() {
    val arr1 = arrayOf(
        intArrayOf(2, 3, 2),
        intArrayOf(4, 2, 4),
        intArrayOf(3, 1, 4)
    )
    val arr2 = arrayOf(
        intArrayOf(5, 4, 3),
        intArrayOf(2, 4, 1),
        intArrayOf(3, 1, 1)
    )
    val answer = MatrixMultiplication().solution(arr1, arr2)
    for (i in answer.indices) {
        println(answer[i].joinToString(" "))
    }
}