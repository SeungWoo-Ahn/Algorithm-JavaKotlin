package programmers.Practice.Level1

class MatrixAddition {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) {IntArray(arr1[0].size)}
        for(i in arr1.indices) {
            for(j in arr1[i].indices) {
                answer[i][j] = arr1[i][j] + arr2[i][j]
            }
        }
        return answer
    }
}

fun main() {
    val answer = MatrixAddition().solution(
        arr1 = arrayOf(intArrayOf(1), intArrayOf(2)),
        arr2 = arrayOf(intArrayOf(3), intArrayOf(4))
    )
    for(i in answer) {
        println(i.contentToString())
    }
}