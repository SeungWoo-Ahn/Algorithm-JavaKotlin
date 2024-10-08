package programmers.Practice.Level2

import java.util.LinkedList
import java.util.Queue

class IslandTrip {
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun cntIslandFood(
        sx: Int,
        sy: Int,
        maps: Array<String>,
        visited: Array<BooleanArray>
    ): Int {
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        var foodCnt = 0
        q += sx to sy
        visited[sx][sy] = true
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            foodCnt += maps[x][y] - '0'
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny, maps) || visited[nx][ny]) continue
                if (maps[nx][ny] == 'X') continue
                visited[nx][ny] = true
                q += nx to ny
            }
        }
        return foodCnt
    }

    private fun outOfBoundary(x: Int, y: Int, maps: Array<String>): Boolean {
        return x !in maps.indices || y !in maps[x].indices
    }

    fun solution(maps: Array<String>): IntArray {
        val result = mutableListOf<Int>()
        val visited = Array(maps.size) { BooleanArray(maps[0].length) }
        for (x in maps.indices) {
            for (y in maps[x].indices) {
                if (maps[x][y] != 'X' && visited[x][y].not()) {
                    result += cntIslandFood(x, y, maps, visited)
                }
            }
        }
        return if (result.isEmpty()) intArrayOf(-1)
        else result.sorted().toIntArray()
    }
}

fun main() {
    val maps = arrayOf("X591X","X1X5X","X231X", "1XXX1")
    val answer = IslandTrip().solution(maps)
    print(answer.joinToString())
}