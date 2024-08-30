package programmers.Practice.Level3

class ColumnsBeamsInstallation {
    private var size = 0
    private var map: Array<Array<BooleanArray>> = arrayOf()

    private fun isColumnSafe(x: Int, y: Int): Boolean {
        if (y == 0) return true
        if (x + 1 <= size && map[x][y][1]) return true
        if (x - 1 >= 0 && map[x - 1][y][1]) return true
        if (y - 1 >= 0 && map[x][y - 1][0]) return true
        return false
    }

    private fun isBeamSafe(x: Int, y: Int): Boolean {
        if (y - 1 >= 0 && map[x][y - 1][0]) return true
        if (y - 1 >= 0 && map[x + 1][y - 1][0]) return true
        if (x - 1 >= 0 && x + 1 <= size && map[x - 1][y][1] && map[x + 1][y][1]) return true
        return false
    }

    private fun canInsert(a: Int, x: Int, y: Int): Boolean {
        return if (a == 0) isColumnSafe(x, y)
        else isBeamSafe(x, y)
    }

    private fun canDelete(a: Int, x: Int, y: Int): Boolean {
        var isSafe = true
        map[x][y][a] = false
        if (a == 0) {
            if (x - 1 >= 0 && map[x - 1][y + 1][1] && !isBeamSafe(x - 1, y + 1)) {
                isSafe = false
            } else if (x + 1 <= size && map[x][y + 1][1] && !isBeamSafe(x, y + 1)) {
                isSafe = false
            } else if (y + 2 <= size && map[x][y + 1][0] && !isColumnSafe(x, y + 1)) {
                isSafe = false
            }
        } else {
            if (y + 1 <= size && map[x][y][0] && !isColumnSafe(x, y)) {
                isSafe = false
            } else if (y + 1 <= size && map[x + 1][y][0] && !isColumnSafe(x + 1, y)) {
                isSafe = false
            } else if (x - 1 >= 0 && map[x - 1][y][1] && !isBeamSafe(x - 1, y)) {
                isSafe = false
            } else if (x + 2 <= size && map[x + 1][y][1] && !isBeamSafe(x + 1, y)) {
                isSafe = false
            }
        }
        if (!isSafe) {
            map[x][y][a] = true
            return false
        }
        return true
    }

    fun solution(n: Int, buildFrame: Array<IntArray>): Array<IntArray> {
        var resultCnt = 0
        size = n
        map = Array(n + 1) { Array(n + 1) { BooleanArray(2) } }
        for ((x, y, a, b) in buildFrame) {
            if (b == 1) {
                if (canInsert(a, x, y)) {
                    map[x][y][a] = true
                    resultCnt++
                }
            } else {
                if (canDelete(a, x, y)) {
                    resultCnt--
                }
            }
        }
        val result = Array(resultCnt) { IntArray(3) }
        var idx = 0
        for (i in 0..n) {
            for (j in 0..n) {
                for (a in 0..1) {
                    if (map[i][j][a]) {
                        result[idx][0] = i
                        result[idx][1] = j
                        result[idx][2] = a
                        idx++
                    }
                }
            }
        }
        return result
    }
}

fun main() {
    val n = 5
    val buildFrame = arrayOf(
        intArrayOf(1, 0, 0, 1),
        intArrayOf(1, 1, 1, 1),
        intArrayOf(2, 1, 0, 1),
        intArrayOf(2, 2, 1, 1),
        intArrayOf(5, 0, 0, 1),
        intArrayOf(5, 1, 0, 1),
        intArrayOf(4, 2, 1, 1),
        intArrayOf(3, 2, 1, 1)
    )
    val answer = ColumnsBeamsInstallation().solution(n, buildFrame)
    for (arr in answer) {
        println(arr.joinToString(","))
    }
}