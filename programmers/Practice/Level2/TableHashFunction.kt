package programmers.Practice.Level2

class TableHashFunction {
    fun solution(data: Array<IntArray>, col: Int, rowBegin: Int, rowEnd: Int): Int {
        data.sortWith(compareBy({ it[col - 1] }, { -it.first() }))
        return (rowBegin..rowEnd).map { i ->
            data[i - 1].sumOf { num -> num % i }
        }.reduce { acc, num -> acc xor num }
    }
}

fun main() {
    val data = arrayOf(
        intArrayOf(2, 2, 6),
        intArrayOf(1, 5, 10),
        intArrayOf(4, 2, 9),
        intArrayOf(3, 8, 3)
    )
    val col = 2
    val rowBegin = 2
    val rowEnd = 3
    val answer = TableHashFunction().solution(data, col, rowBegin, rowEnd)
    print(answer)
}