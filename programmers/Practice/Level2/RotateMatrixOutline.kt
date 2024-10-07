package programmers.Practice.Level2

class RotateMatrixOutline {
    private fun rotate(matrix: Array<IntArray>, query: IntArray): Int {
        val (x1, y1, x2, y2) = query.map { it - 1 }
        var min = 10_001
        var prev = matrix[x1][y1]
        var cur: Int
        for (y in y1 + 1..y2) {
            cur = matrix[x1][y]
            matrix[x1][y] = prev
            min = minOf(min, prev)
            prev = cur
        }
        for (x in x1 + 1..x2) {
            cur = matrix[x][y2]
            matrix[x][y2] = prev
            min = minOf(min, prev)
            prev = cur
        }
        for (y in y2 - 1 downTo y1) {
            cur = matrix[x2][y]
            matrix[x2][y] = prev
            min = minOf(min, prev)
            prev = cur
        }
        for (x in x2 - 1 downTo x1) {
            cur = matrix[x][y1]
            matrix[x][y1] = prev
            min = minOf(min, prev)
            prev = cur
        }
        return min
    }

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val matrix = Array(rows) { x ->
            IntArray(columns) { y ->
                x * columns + y + 1
            }
        }
        return IntArray(queries.size) { idx ->
            rotate(matrix, queries[idx])
        }
    }
}

fun main() {
    val rows = 6
    val columns = 6
    val queries = arrayOf(
        intArrayOf(2,2,5,4),
        intArrayOf(3,3,6,6),
        intArrayOf(5,1,6,3)
    )
    val answer = RotateMatrixOutline().solution(rows, columns, queries)
    print(answer.joinToString())
}