package programmers.Practice.Level2

class LiftAndCrane {
    private lateinit var map: Array<CharArray>
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun useCart(n: Int, m: Int, target: Char) {
        val visited = Array(n) { BooleanArray(m) }
        for (x in map.indices) {
            if (visited[x][0].not()) {
                dfs(x, 0, target, visited)
            }
            if (visited[x][m - 1].not()) {
                dfs(x, m - 1, target, visited)
            }
        }
        for (y in map[0].indices) {
            if (visited[0][y].not()) {
                dfs(0, y, target, visited)
            }
            if (visited[n - 1][y].not()) {
                dfs(n - 1, y, target, visited)
            }
        }
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] == INSIDE) {
                    map[x][y] = OUTSIDE
                }
            }
        }
    }

    private fun dfs(x: Int, y: Int, target: Char, visited: Array<BooleanArray>) {
        visited[x][y] = true
        if (map[x][y] == target) {
            map[x][y] = INSIDE
            return
        }
        if (map[x][y] != OUTSIDE) return
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (outOfBoundary(nx, ny) || visited[nx][ny]) continue
            dfs(nx, ny, target, visited)
        }
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun useCrane(target: Char) {
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] == target) {
                    map[x][y] = OUTSIDE
                }
            }
        }
    }

    private fun getRemainCnt(): Int {
        var cnt = 0
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] != OUTSIDE) {
                    cnt++
                }
            }
        }
        return cnt
    }

    fun solution(storage: Array<String>, requests: Array<String>): Int {
        init(storage)
        val (n, m) = storage.getSize()
        for (request in requests) {
            val target = request.first()
            when (request.length) {
                1 -> useCart(n, m, target)
                2 -> useCrane(target)
            }
        }
        return getRemainCnt()
    }

    private fun init(storage: Array<String>) {
        map = Array(storage.size) { storage[it].toCharArray() }
    }

    private fun Array<String>.getSize(): Pair<Int, Int> {
        return this.size to this[0].length
    }

    companion object {
        private const val OUTSIDE = '0'
        private const val INSIDE = '1'
    }
}

fun main() {
    val storage = arrayOf("AZWQY", "CAABX", "BBDDA", "ACACA")
    val requests = arrayOf("A", "BB", "A")
    val answer = LiftAndCrane().solution(storage, requests)
    print(answer)
}