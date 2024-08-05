package programmers.DfsBfs

class PickUpItem {
    private val map = Array(101) { IntArray(101) }
    private val visited = Array(101) { BooleanArray(101) }
    private val d = listOf(
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, 1),
        Pair(0, -1)
    )
    private var answer = 10_000

    private fun List<Int>.insertRectangle() {
        for (y in this[1]..this[3]) {
            if (map[this[0]][y] == NONE) {
                map[this[0]][y] = SIDE
            }
            if (map[this[2]][y] == NONE) {
                map[this[2]][y] = SIDE
            }
        }
        for (x in this[0]..this[2]) {
            if (map[x][this[1]] == NONE) {
                map[x][this[1]] = SIDE
            }
            if (map[x][this[3]] == NONE) {
                map[x][this[3]] = SIDE
            }
        }
        for (x in this[0] + 1 until this[2]) {
            for (y in this[1] + 1 until this[3]) {
                map[x][y] = INSIDE
            }
        }
    }

    private fun dfs(depth: Int, x: Int, y: Int, itemX: Int, itemY: Int) {
        if (x == itemX && y == itemY) {
            answer = minOf(answer, depth / 2)
            return
        }
        visited[x][y] = true
        for ((dx, dy) in d) {
            val nx = x + dx
            val ny = y + dy
            if (outOfBoundary(nx, ny) || visited[nx][ny]) continue
            if (map[nx][ny] != SIDE) continue
            dfs(depth + 1, nx, ny, itemX, itemY)
        }
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in 2..100 || y !in 2..100
    }

    fun solution(rectangle: Array<IntArray>, characterX: Int, characterY: Int, itemX: Int, itemY: Int): Int {
        for (rec in rectangle) {
            rec.map { it * 2 }.insertRectangle()
        }
        dfs(0, characterX * 2, characterY * 2, itemX * 2, itemY * 2)
        return answer
    }

    companion object {
        private const val SIDE = 1
        private const val NONE = 0
        private const val INSIDE = -1
    }
}

fun main() {
    val rectangle = arrayOf(
        intArrayOf(1,1,7,4),
        intArrayOf(3,2,5,5),
        intArrayOf(4,3,6,9),
        intArrayOf(2,6,8,8)
    )
    val answer = PickUpItem().solution(rectangle, 1, 3, 7, 8)
    print(answer)
}