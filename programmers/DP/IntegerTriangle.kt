package programmers.DP

class IntegerTriangle {
    fun solution(triangle: Array<IntArray>): Int {
        for (i in 1 until triangle.size) {
            triangle[i][0] += triangle[i - 1][0]
            triangle[i][i] += triangle[i - 1][i - 1]
            for (j in 1 until i) {
                triangle[i][j] += maxOf(triangle[i - 1][j - 1], triangle[i - 1][j])
            }
        }
        return triangle.last().max()
    }
}

fun main() {
    val triangle = arrayOf(
        intArrayOf(7),
        intArrayOf(3, 8),
        intArrayOf(8, 1, 0),
        intArrayOf(2, 7, 4, 4),
        intArrayOf(4, 5, 2, 6, 5),
    )
    val answer = IntegerTriangle().solution(triangle)
    print(answer)
}