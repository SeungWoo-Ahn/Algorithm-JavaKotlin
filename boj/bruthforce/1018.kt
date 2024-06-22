package boj.bruthforce

class `1018` {
    private var board: Array<CharArray> = arrayOf()

    private fun paintBoard(sx: Int, sy: Int, firstColor: Char): Int {
        var paintCnt = 0
        for (x in sx until sx + 8) {
            for (y in sy until sy + 8) {
                if (!isRightColor(x, y, sx, sy, firstColor))
                    paintCnt++
            }
        }
        return paintCnt
    }

    private fun isRightColor(x: Int, y: Int, sx: Int, sy: Int, firstColor: Char): Boolean {
        val rx = x - sx
        val ry = y - sy
        return if ((rx + ry) % 2 == 0) board[x][y] == firstColor
        else board[x][y] == oppositeColor(firstColor)
    }

    private fun oppositeColor(color: Char): Char {
        return when (color) {
            'W' -> 'B'
            'B' -> 'W'
            else -> throw IllegalArgumentException()
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        board = Array(n) { readLine().toCharArray() }
        var answer = 8 * 8
        for (i in 0 .. n - 8) {
            for (j in 0 .. m - 8) {
                val minCnt = minOf(paintBoard(i, j, 'W'), paintBoard(i, j, 'B'))
                answer = minOf(answer, minCnt)
            }
        }
        print(answer)
    }
}

fun main() {
    `1018`().solution()
}